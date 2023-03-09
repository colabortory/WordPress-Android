package org.wordpress.android.ui.bloggingreminders

import org.wordpress.android.ui.prefs.AppPrefsWrapper
import org.wordpress.android.util.config.featureflags.remote.BloggingRemindersFeatureFlag
import javax.inject.Inject

class BloggingRemindersManager
@Inject constructor(
    private val bloggingRemindersFeatureConfig: BloggingRemindersFeatureFlag,
    private val appPrefsWrapper: AppPrefsWrapper
) {
    fun shouldShowBloggingRemindersPrompt(siteId: Int): Boolean {
        return bloggingRemindersFeatureConfig.isEnabled() && !appPrefsWrapper.isBloggingRemindersShown(siteId)
    }

    fun bloggingRemindersShown(siteId: Int) {
        appPrefsWrapper.setBloggingRemindersShown(siteId)
    }
}
