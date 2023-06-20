package org.wordpress.android.ui.debug

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.wordpress.android.ui.debug.DebugSettingsType.FEATURES_IN_DEVELOPMENT
import org.wordpress.android.ui.debug.DebugSettingsType.REMOTE_FEATURES
import org.wordpress.android.ui.debug.DebugSettingsType.REMOTE_FIELD_CONFIGS

class DebugSettingsTabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    inline fun <reified T> FragmentManager.runOn(block: T?.() -> Unit) {
        val first = 0.rangeTo(itemCount).map { findFragmentById(it) as? T }.first()
        first.block()
    }

    override fun createFragment(position: Int) =
        when (position) {
            0 -> DebugSettingsFragment.newInstance(REMOTE_FEATURES)
            1 -> DebugSettingsFragment.newInstance(REMOTE_FIELD_CONFIGS)
            2 -> DebugSettingsFragment.newInstance(FEATURES_IN_DEVELOPMENT)
            else -> DebugSettingsFragment()
        }
}

