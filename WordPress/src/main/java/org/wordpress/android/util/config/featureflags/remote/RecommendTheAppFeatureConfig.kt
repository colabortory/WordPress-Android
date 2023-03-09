package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import org.wordpress.android.util.config.featureflags.remote.RecommendTheAppFeatureConfig.Companion.RECOMMEND_THE_APP_FIELD
import javax.inject.Inject

@RemoteFeatureFlagDefault(RECOMMEND_THE_APP_FIELD, true)
class RecommendTheAppFeatureConfig @Inject constructor(appConfig: AppConfig) : FeatureConfig(
    appConfig,
    BuildConfig.RECOMMEND_THE_APP,
    RECOMMEND_THE_APP_FIELD
) {
    companion object {
        const val RECOMMEND_THE_APP_FIELD = "recommend_the_app_enabled"
    }
}
