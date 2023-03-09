package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import javax.inject.Inject

/**
 * Configuration of the 'Quick Start for Existing Users V2' that will introduce a new set of
 * Quick Start steps that are relevant to existing users.
 */
@RemoteFeatureFlagDefault(
    remoteField = QuickStartExistingUsersV2FeatureFlag.QUICK_START_EXISTING_USERS_V2,
    defaultValue = true
)
class QuickStartExistingUsersV2FeatureFlag @Inject constructor(
    appConfig: AppConfig
) : FeatureFlag(
    appConfig,
    BuildConfig.QUICK_START_EXISTING_USERS_V2,
    QUICK_START_EXISTING_USERS_V2
) {
    companion object {
        const val QUICK_START_EXISTING_USERS_V2 = "quick_start_existing_users_v2"
    }
}
