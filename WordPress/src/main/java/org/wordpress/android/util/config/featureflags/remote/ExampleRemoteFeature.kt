package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import org.wordpress.android.util.config.featureflags.remote.ExampleRemoteFeature.Companion.EXAMPLE_REMOTE_FEATURE_FIELD
import javax.inject.Inject

/**
 * Configuration of an example remote feature
 */
@Suppress("Unused")
@RemoteFeatureFlagDefault(remoteField = EXAMPLE_REMOTE_FEATURE_FIELD, defaultValue = false)
class ExampleRemoteFeature
@Inject constructor(appConfig: AppConfig) : FeatureConfig(
    appConfig,
    false,
    EXAMPLE_REMOTE_FEATURE_FIELD
) {
    companion object {
        const val EXAMPLE_REMOTE_FEATURE_FIELD = "example_remote_feature_field"
    }
}
