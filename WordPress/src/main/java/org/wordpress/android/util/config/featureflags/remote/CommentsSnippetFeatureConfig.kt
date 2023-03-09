package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import org.wordpress.android.util.config.featureflags.remote.CommentsSnippetFeatureConfig.Companion.COMMENTS_SNIPPET_COMMENTS_REMOTE_FIELD
import javax.inject.Inject

/**
 * Configuration of the threaded comments below post improvement
 */
@RemoteFeatureFlagDefault(COMMENTS_SNIPPET_COMMENTS_REMOTE_FIELD, true)
class CommentsSnippetFeatureConfig
@Inject constructor(
    appConfig: AppConfig
) : FeatureConfig(
    appConfig,
    BuildConfig.COMMENTS_SNIPPET,
    COMMENTS_SNIPPET_COMMENTS_REMOTE_FIELD
) {
    companion object {
        const val COMMENTS_SNIPPET_COMMENTS_REMOTE_FIELD = "comments_snippet_remote_field"
    }
}
