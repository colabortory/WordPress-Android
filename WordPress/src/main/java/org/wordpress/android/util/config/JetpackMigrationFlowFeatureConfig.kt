package org.wordpress.android.util.config

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.LocalFeatureFlagDefault
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
