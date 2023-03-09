package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import org.wordpress.android.util.config.featureflags.remote.ReaderCommentsModerationFeatureConfig.Companion.READER_COMMENTS_MODERATION_REMOTE_FIELD
import javax.inject.Inject

/**
 * Configuration of the reader comments moderation
 */
@RemoteFeatureFlagDefault(READER_COMMENTS_MODERATION_REMOTE_FIELD, true)
class ReaderCommentsModerationFeatureConfig
@Inject constructor(
    appConfig: AppConfig
) : FeatureConfig(
    appConfig,
    BuildConfig.READER_COMMENTS_MODERATION,
    READER_COMMENTS_MODERATION_REMOTE_FIELD
) {
    companion object {
        const val READER_COMMENTS_MODERATION_REMOTE_FIELD = "reader_comments_moderation_field"
    }
}
