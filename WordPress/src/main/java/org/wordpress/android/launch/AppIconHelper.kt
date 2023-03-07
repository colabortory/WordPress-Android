package org.wordpress.android.launch

import org.wordpress.android.ui.prefs.AppPrefsWrapper
import org.wordpress.android.util.PackageManagerWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppIconHelper @Inject constructor(
    private val packageManagerWrapper: PackageManagerWrapper,
    private val appPrefsWrapper: AppPrefsWrapper,
) {
    private val appIcons = AppIcon.values()

    fun getCurrentIcon(): AppIcon = AppIcon.fromId(appPrefsWrapper.currentAppIconId)

    fun setCurrentIcon(icon: AppIcon) {
        enableSelectedIcon(icon)
        appPrefsWrapper.currentAppIconId = icon.id
    }

    private fun enableSelectedIcon(icon: AppIcon) {
        appIcons.forEach {
            if (it == icon) {
                packageManagerWrapper.enableComponentEnabledSetting(it.alias)
            } else {
                packageManagerWrapper.disableComponentEnabledSetting(it.alias)
            }
        }
    }
}
