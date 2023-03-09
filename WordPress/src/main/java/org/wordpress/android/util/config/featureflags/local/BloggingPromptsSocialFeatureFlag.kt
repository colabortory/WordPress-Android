package org.wordpress.android.util.config.featureflags.local

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.LocalFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.LocalFeatureFlag
import javax.inject.Inject

@LocalFeatureFlagDefault(BuildConfig.BLOGGING_PROMPTS_SOCIAL)
class BloggingPromptsSocialFeatureFlag
@Inject constructor(appConfig: AppConfig) : LocalFeatureFlag(
    appConfig
) {
    override fun isEnabled(): Boolean {
        return super.isEnabled() && BuildConfig.IS_JETPACK_APP
    }
}
