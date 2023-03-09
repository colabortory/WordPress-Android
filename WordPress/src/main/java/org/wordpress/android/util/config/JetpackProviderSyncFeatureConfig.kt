package org.wordpress.android.util.config

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.JetpackProviderSyncFeatureConfig.Companion.JETPACK_PROVIDER_SYNC_REMOTE_FIELD
import javax.inject.Inject

@RemoteFeatureFlagDefault(JETPACK_PROVIDER_SYNC_REMOTE_FIELD, false)
class JetpackProviderSyncFeatureConfig
@Inject constructor(
    appConfig: AppConfig
) : FeatureConfig(
    appConfig,
    BuildConfig.JETPACK_PROVIDER_SYNC,
    JETPACK_PROVIDER_SYNC_REMOTE_FIELD
) {
    companion object {
        const val JETPACK_PROVIDER_SYNC_REMOTE_FIELD = "provider_sync_remote_field"
    }
}

