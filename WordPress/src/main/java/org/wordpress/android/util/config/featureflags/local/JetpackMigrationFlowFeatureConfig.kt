package org.wordpress.android.util.config.featureflags.local

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.LocalFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import javax.inject.Inject

/**
 * Configuration of the Jetpack Migration Flow.
 */
@LocalFeatureFlagDefault
class JetpackMigrationFlowFeatureConfig
@Inject constructor(appConfig: AppConfig) : FeatureConfig(appConfig, BuildConfig.JETPACK_MIGRATION_FLOW) {
    override fun isEnabled(): Boolean {
        return BuildConfig.IS_JETPACK_APP && super.isEnabled()
    }
}
