package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import org.wordpress.android.util.config.featureflags.remote.JetpackFeatureRemovalSelfHostedUsersConfig.Companion.JETPACK_FEATURE_REMOVAL_SELF_HOSTED_USERS_REMOTE_FIELD
import javax.inject.Inject

/**
 * Configuration for Jetpack feature removal phase new users
 */
@RemoteFeatureFlagDefault(JETPACK_FEATURE_REMOVAL_SELF_HOSTED_USERS_REMOTE_FIELD, false)
class JetpackFeatureRemovalSelfHostedUsersConfig @Inject constructor(
    appConfig: AppConfig
) : FeatureConfig(
    appConfig,
    BuildConfig.JETPACK_FEATURE_REMOVAL_SELF_HOSTED_USERS,
    JETPACK_FEATURE_REMOVAL_SELF_HOSTED_USERS_REMOTE_FIELD
) {
    companion object {
        const val JETPACK_FEATURE_REMOVAL_SELF_HOSTED_USERS_REMOTE_FIELD = "jp_removal_self_hosted"
    }
}
