package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import org.wordpress.android.util.config.featureflags.remote.RecommendTheAppFeatureFlag.Companion.RECOMMEND_THE_APP_FIELD
import javax.inject.Inject

@RemoteFeatureFlagDefault(RECOMMEND_THE_APP_FIELD, true)
class RecommendTheAppFeatureFlag @Inject constructor(appConfig: AppConfig) : FeatureFlag(
    appConfig,
    BuildConfig.RECOMMEND_THE_APP,
    RECOMMEND_THE_APP_FIELD
) {
    companion object {
        const val RECOMMEND_THE_APP_FIELD = "recommend_the_app_enabled"
    }
}
