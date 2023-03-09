package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import org.wordpress.android.util.config.featureflags.remote.UnifiedCommentsDetailFeatureConfig.Companion.UNIFIED_COMMENTS_DETAILS_REMOTE_FIELD
import javax.inject.Inject

/**
 * Configuration of the Unified Comments list improvements
 */
@RemoteFeatureFlagDefault(UNIFIED_COMMENTS_DETAILS_REMOTE_FIELD, false)
class UnifiedCommentsDetailFeatureConfig
@Inject constructor(
    appConfig: AppConfig
) : FeatureConfig(
    appConfig,
    BuildConfig.UNIFIED_COMMENTS_DETAILS,
    UNIFIED_COMMENTS_DETAILS_REMOTE_FIELD
) {
    companion object {
        const val UNIFIED_COMMENTS_DETAILS_REMOTE_FIELD = "unified_comments_detail_remote_field"
    }
}
