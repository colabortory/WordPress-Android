package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import org.wordpress.android.util.config.featureflags.remote.JetpackLocalUserFlagsFeatureFlag.Companion.JETPACK_LOCAL_USER_FLAGS_REMOTE_FIELD
import javax.inject.Inject

@RemoteFeatureFlagDefault(JETPACK_LOCAL_USER_FLAGS_REMOTE_FIELD, false)
class JetpackLocalUserFlagsFeatureFlag
@Inject constructor(
    appConfig: AppConfig
) : FeatureFlag(
    appConfig,
    BuildConfig.JETPACK_LOCAL_USER_FLAGS,
    JETPACK_LOCAL_USER_FLAGS_REMOTE_FIELD
) {
    companion object {
        const val JETPACK_LOCAL_USER_FLAGS_REMOTE_FIELD = "jetpack_local_user_flags_remote_field"
    }
}
