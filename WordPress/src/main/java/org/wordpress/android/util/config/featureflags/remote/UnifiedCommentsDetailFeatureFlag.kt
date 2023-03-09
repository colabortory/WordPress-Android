package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import org.wordpress.android.util.config.featureflags.remote.UnifiedCommentsDetailFeatureFlag.Companion.UNIFIED_COMMENTS_DETAILS_REMOTE_FIELD
import javax.inject.Inject

/**
 * Configuration of the Unified Comments list improvements
 */
@RemoteFeatureFlagDefault(UNIFIED_COMMENTS_DETAILS_REMOTE_FIELD, false)
class UnifiedCommentsDetailFeatureFlag
@Inject constructor(
    appConfig: AppConfig
) : FeatureFlag(
    appConfig,
    BuildConfig.UNIFIED_COMMENTS_DETAILS,
    UNIFIED_COMMENTS_DETAILS_REMOTE_FIELD
) {
    companion object {
        const val UNIFIED_COMMENTS_DETAILS_REMOTE_FIELD = "unified_comments_detail_remote_field"
    }
}
