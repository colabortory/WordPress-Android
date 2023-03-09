package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import org.wordpress.android.util.config.featureflags.remote.UnifiedCommentsCommentEditFeatureFlag.Companion.UNIFIED_COMMENTS_EDIT_REMOTE_FIELD
import javax.inject.Inject

@RemoteFeatureFlagDefault(UNIFIED_COMMENTS_EDIT_REMOTE_FIELD, true)
class UnifiedCommentsCommentEditFeatureFlag @Inject constructor(appConfig: AppConfig) : FeatureFlag(
    appConfig,
    BuildConfig.UNIFIED_COMMENTS_COMMENT_EDIT,
    UNIFIED_COMMENTS_EDIT_REMOTE_FIELD
) {
    companion object {
        const val UNIFIED_COMMENTS_EDIT_REMOTE_FIELD = "unified_comments_edit_remote_field"
    }
}
