package org.wordpress.android.ui.mediauploads

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.wordpress.android.fluxc.store.AccountStore
import org.wordpress.android.fluxc.store.SiteStore
import org.wordpress.android.ui.ActivityLauncher
import org.wordpress.android.ui.domains.management.M3Theme
import org.wordpress.android.ui.media.MediaBrowserType
import org.wordpress.android.ui.mediauploads.FastMediaUploadViewModel.ActionEvent.CloseScreen
import org.wordpress.android.ui.mediauploads.FastMediaUploadViewModel.ActionEvent.OpenMediaPicker
import org.wordpress.android.ui.mediauploads.composable.FastMediaUploadScreen
import org.wordpress.android.ui.mysite.SelectedSiteRepository
import org.wordpress.android.ui.photopicker.MediaPickerLauncher
import org.wordpress.android.util.AppLog
import org.wordpress.android.util.extensions.setContent
import javax.inject.Inject

@AndroidEntryPoint
class FastMediaUploadActivity : AppCompatActivity() {
    @Inject
    lateinit var selectedSiteRepository: SelectedSiteRepository
    @Inject
    lateinit var accountStore: AccountStore
    @Inject
    lateinit var siteStore: SiteStore

    private val viewModel: FastMediaUploadViewModel by viewModels()

    @Inject
    lateinit var mediaPickerLauncher: MediaPickerLauncher

    private val siteIdArg: String get() = intent.getStringExtra(SITE_ID_KEY) ?: error("Site id cannot be null.")
    private val postIdArg: String get() = intent.getStringExtra(POST_ID_KEY) ?: error("Post id cannot be null.")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            M3Theme {
                FastMediaUploadScreen(
                    onPickMediaTapped = viewModel::onPickMediaTapped,
                    onCloseTapped = viewModel::onCloseTapped,
                )
            }
        }
        observeActions()
    }

    private fun observeActions() {
        viewModel.actionEvents.onEach(this::handleActionEvents).launchIn(lifecycleScope)
    }

    private fun handleActionEvents(actionEvent: FastMediaUploadViewModel.ActionEvent) {
        when (actionEvent) {
            is CloseScreen -> onBackPressedDispatcher.onBackPressed()
            is OpenMediaPicker -> {
                val selectedSiteLocalId = selectedSiteRepository.getSelectedSiteLocalId(true)
                val site = siteStore.getSiteByLocalId(selectedSiteLocalId)

                // TODO Get site model from remote site id and pass post id for upload
                AppLog.d(AppLog.T.MEDIA, "FastMediaUploadActivity(site=$siteIdArg, post=$postIdArg")

                site?.let {
                    ActivityLauncher.viewMediaPickerForResult(this, site, MediaBrowserType.WP_STORIES_MEDIA_PICKER)
                }
            }
        }
    }

    companion object {
        const val SITE_ID_KEY: String = "site_id_key"
        const val POST_ID_KEY: String = "post_id_key"
    }
}
