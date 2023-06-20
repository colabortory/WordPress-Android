package org.wordpress.android.ui.debug

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import org.wordpress.android.R
import org.wordpress.android.databinding.DebugSettingsActivityBinding
import org.wordpress.android.ui.ActivityLauncher
import org.wordpress.android.ui.LocaleAwareActivity
import org.wordpress.android.ui.debug.DebugSettingsViewModel.NavigationAction.DebugCookies
import org.wordpress.android.ui.debug.DebugSettingsViewModel.NavigationAction.PreviewFragment
import org.wordpress.android.ui.debug.previews.PreviewFragmentActivity.Companion.CLASS_PREVIEW
import org.wordpress.android.ui.debug.previews.PreviewFragmentActivity.Companion.CODE_PREVIEW
import org.wordpress.android.ui.debug.previews.PreviewFragmentActivity.Companion.KEY
import org.wordpress.android.viewmodel.observeEvent
import javax.inject.Inject

@AndroidEntryPoint
class DebugSettingsActivity : LocaleAwareActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: DebugSettingsViewModel
    private lateinit var _adapter: DebugSettingsTabAdapter
    private lateinit var _binding: DebugSettingsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DebugSettingsActivityBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setUpToolbar()
            setup()
            setupTabLayout()
        }
        setUpViewModel()
    }

    private fun DebugSettingsActivityBinding.setUpToolbar() {
        setSupportActionBar(toolbarMain)
        supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun DebugSettingsActivityBinding.setup() = viewPager.apply {
        if (adapter == null) {
            with(this@DebugSettingsActivity) {
                _adapter = DebugSettingsTabAdapter(this).also {
                    adapter = it
                }
            }
        }
    }

    private fun DebugSettingsActivityBinding.setupTabLayout() {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setText(
                when (position) {
                    0 -> R.string.debug_settings_remote_features
                    1 -> R.string.debug_settings_remote_field_configs
                    2 -> R.string.debug_settings_features_in_development
                    else -> error("Invalid position: $position")
                }
            )
        }.attach()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(viewModelStore, viewModelFactory)[DebugSettingsViewModel::class.java]
        viewModel.onNavigation.observeEvent(this) {
            when (it) {
                is DebugCookies -> ActivityLauncher.viewDebugCookies(this)
                is PreviewFragment -> preview(it.name)
            }
        }
    }

    private fun preview(key: String) = _adapter.run {
        supportFragmentManager.runOn<DebugSettingsFragment> {
            requireNotNull(this as? Fragment) { "Cannot find fragment" }.also {
                val intent = Intent(it.requireContext(), CLASS_PREVIEW).apply { putExtra(KEY, key) }
                startActivityFromFragment(it, intent, CODE_PREVIEW)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.debug_settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressedDispatcher.onBackPressed()
            R.id.menu_debug_cookies -> viewModel.onDebugCookiesClick()
            R.id.menu_restart_app -> viewModel.onRestartAppClick()
            R.id.menu_show_weekly_notifications -> viewModel.onForceShowWeeklyRoundupClick()
        }
        return true
    }
}
