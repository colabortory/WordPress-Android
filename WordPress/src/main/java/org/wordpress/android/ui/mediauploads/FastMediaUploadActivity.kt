package org.wordpress.android.ui.mediauploads

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.wordpress.android.WordPress
import org.wordpress.android.fluxc.model.SiteModel
import org.wordpress.android.ui.ActivityLauncher
import org.wordpress.android.ui.RequestCodes
import org.wordpress.android.ui.domains.management.M3Theme
import org.wordpress.android.ui.media.MediaBrowserActivity
import org.wordpress.android.ui.media.MediaBrowserType
import org.wordpress.android.ui.mediauploads.FastMediaUploadViewModel.ActionEvent.CloseScreen
import org.wordpress.android.ui.mediauploads.FastMediaUploadViewModel.ActionEvent.OpenMediaPicker
import org.wordpress.android.ui.mediauploads.composable.FastMediaUploadScreen
import org.wordpress.android.ui.photopicker.MediaPickerLauncher
import org.wordpress.android.util.AppLog
import org.wordpress.android.util.extensions.setContent
import javax.inject.Inject

@AndroidEntryPoint
class FastMediaUploadActivity : AppCompatActivity() {
    private val viewModel: FastMediaUploadViewModel by viewModels()

    private val resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val key = MediaBrowserActivity.RESULT_IDS
            val data = result.data ?: return@registerForActivityResult
            if (data.hasExtra(key)) {
                val mediaIds: LongArray = data.getLongArrayExtra(key) ?: return@registerForActivityResult
                AppLog.d(AppLog.T.MEDIA, mediaIds.joinToString(","))
            }
        }
    }

    @Inject
    lateinit var mediaPickerLauncher: MediaPickerLauncher

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
            is OpenMediaPicker -> ActivityLauncher.viewMediaPickerForResult(this, SiteModel(), MediaBrowserType.WP_STORIES_MEDIA_PICKER)
        }
    }
}
