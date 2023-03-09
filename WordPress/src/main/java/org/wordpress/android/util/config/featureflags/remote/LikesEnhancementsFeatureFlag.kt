package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import org.wordpress.android.util.config.featureflags.remote.LikesEnhancementsFeatureFlag.Companion.LIKES_ENHANCEMENTS_REMOTE_FIELD
import javax.inject.Inject

/**
 * Configuration of the Likes Enhancements feature
 */
@RemoteFeatureFlagDefault(LIKES_ENHANCEMENTS_REMOTE_FIELD, true)
class LikesEnhancementsFeatureFlag
@Inject constructor(appConfig: AppConfig) : FeatureFlag(
    appConfig,
    BuildConfig.LIKES_ENHANCEMENTS,
    LIKES_ENHANCEMENTS_REMOTE_FIELD
) {
    companion object {
        const val LIKES_ENHANCEMENTS_REMOTE_FIELD = "likes_enhancements_enabled"
    }
}
