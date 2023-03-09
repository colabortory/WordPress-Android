package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import org.wordpress.android.util.config.featureflags.remote.JetpackFeatureRemovalPhaseOneFlag.Companion.JETPACK_FEATURE_REMOVAL_PHASE_ONE_REMOTE_FIELD
import javax.inject.Inject

@RemoteFeatureFlagDefault(JETPACK_FEATURE_REMOVAL_PHASE_ONE_REMOTE_FIELD, false)
class JetpackFeatureRemovalPhaseOneFlag @Inject constructor(
    appConfig: AppConfig
) : FeatureFlag(
    appConfig,
    BuildConfig.JETPACK_FEATURE_REMOVAL_PHASE_ONE,
    JETPACK_FEATURE_REMOVAL_PHASE_ONE_REMOTE_FIELD
) {
    companion object {
        const val JETPACK_FEATURE_REMOVAL_PHASE_ONE_REMOTE_FIELD = "jp_removal_one"
    }
}
