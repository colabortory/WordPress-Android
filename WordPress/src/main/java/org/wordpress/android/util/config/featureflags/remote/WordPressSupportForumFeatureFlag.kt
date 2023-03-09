package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import org.wordpress.android.util.config.featureflags.remote.WordPressSupportForumFeatureFlag.Companion.WORDPRESS_SUPPORT_FORUM_REMOTE_FIELD
import javax.inject.Inject

@RemoteFeatureFlagDefault(WORDPRESS_SUPPORT_FORUM_REMOTE_FIELD, false)
class WordPressSupportForumFeatureFlag @Inject constructor(
    appConfig: AppConfig
) : FeatureFlag(
    appConfig,
    BuildConfig.ENABLE_WORDPRESS_SUPPORT_FORUM,
    WORDPRESS_SUPPORT_FORUM_REMOTE_FIELD
) {
    companion object {
        const val WORDPRESS_SUPPORT_FORUM_REMOTE_FIELD = "wordpress_support_forum_remote_field"
    }
}
