package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import org.wordpress.android.util.config.featureflags.remote.JetpackSharedLoginFeatureConfig.Companion.JETPACK_SHARED_LOGIN_REMOTE_FIELD
import javax.inject.Inject

@RemoteFeatureFlagDefault(JETPACK_SHARED_LOGIN_REMOTE_FIELD, false)
class JetpackSharedLoginFeatureConfig
@Inject constructor(
    appConfig: AppConfig
) : FeatureConfig(
    appConfig,
    BuildConfig.JETPACK_SHARED_LOGIN,
    JETPACK_SHARED_LOGIN_REMOTE_FIELD
) {
    companion object {
        const val JETPACK_SHARED_LOGIN_REMOTE_FIELD = "jetpack_shared_login_remote_field"
    }
}
