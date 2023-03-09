package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import javax.inject.Inject

/**
 * Configuration for Jetpack Powered indicators
 */
@RemoteFeatureFlagDefault(JetpackPoweredFeatureFlag.JETPACK_POWERED_REMOTE_FIELD, true)
class JetpackPoweredFeatureFlag @Inject constructor(
    appConfig: AppConfig
) : FeatureFlag(
    appConfig,
    BuildConfig.JETPACK_POWERED,
    JETPACK_POWERED_REMOTE_FIELD
) {
    companion object {
        const val JETPACK_POWERED_REMOTE_FIELD = "jetpack_powered_remote_field"
    }
}
