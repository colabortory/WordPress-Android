package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import org.wordpress.android.util.config.featureflags.remote.BloggingPromptsFeatureConfig.Companion.BLOGGING_PROMPTS_REMOTE_FIELD
import javax.inject.Inject

@RemoteFeatureFlagDefault(BLOGGING_PROMPTS_REMOTE_FIELD, true)
class BloggingPromptsFeatureConfig
@Inject constructor(appConfig: AppConfig) : FeatureConfig(
    appConfig,
    BuildConfig.BLOGGING_PROMPTS,
    BLOGGING_PROMPTS_REMOTE_FIELD
) {
    override fun isEnabled(): Boolean {
        return super.isEnabled() && BuildConfig.IS_JETPACK_APP
    }

    companion object {
        const val BLOGGING_PROMPTS_REMOTE_FIELD = "blogging_prompts_remote_field"
    }
}
