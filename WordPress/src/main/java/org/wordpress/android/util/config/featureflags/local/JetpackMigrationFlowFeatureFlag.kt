package org.wordpress.android.util.config.featureflags.local

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.LocalFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.LocalFeatureFlag
import javax.inject.Inject

/**
 * Configuration of the Jetpack Migration Flow.
 */
@LocalFeatureFlagDefault(BuildConfig.JETPACK_MIGRATION_FLOW)
class JetpackMigrationFlowFeatureFlag
@Inject constructor(appConfig: AppConfig) : LocalFeatureFlag(appConfig) {
    override fun isEnabled(): Boolean {
        return BuildConfig.IS_JETPACK_APP && super.isEnabled()
    }
}
