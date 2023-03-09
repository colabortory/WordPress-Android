package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import javax.inject.Inject

@RemoteFeatureFlagDefault(BloggingPromptsListFeatureFlag.BLOGGING_PROMPTS_LIST_REMOTE_FIELD, true)
class BloggingPromptsListFeatureFlag
@Inject constructor(appConfig: AppConfig) : FeatureFlag(
    appConfig,
    BuildConfig.BLOGGING_PROMPTS_LIST,
    BLOGGING_PROMPTS_LIST_REMOTE_FIELD,
) {
    override fun isEnabled(): Boolean {
        return super.isEnabled() && BuildConfig.IS_JETPACK_APP
    }

    companion object {
        const val BLOGGING_PROMPTS_LIST_REMOTE_FIELD = "blogging_prompts_list_enabled"
    }
}
