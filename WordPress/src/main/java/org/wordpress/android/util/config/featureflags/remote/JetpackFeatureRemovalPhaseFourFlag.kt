package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import org.wordpress.android.util.config.featureflags.remote.JetpackFeatureRemovalPhaseFourFlag.Companion.JETPACK__FEATURE_REMOVAL_PHASE_FOUR_REMOTE_FIELD
import javax.inject.Inject

@RemoteFeatureFlagDefault(JETPACK__FEATURE_REMOVAL_PHASE_FOUR_REMOTE_FIELD, false)
class JetpackFeatureRemovalPhaseFourFlag @Inject constructor(
    appConfig: AppConfig
) : FeatureFlag(
    appConfig,
    BuildConfig.JETPACK_FEATURE_REMOVAL_PHASE_FOUR,
    JETPACK__FEATURE_REMOVAL_PHASE_FOUR_REMOTE_FIELD
) {
    companion object {
        const val JETPACK__FEATURE_REMOVAL_PHASE_FOUR_REMOTE_FIELD = "jp_removal_four"
    }
}
