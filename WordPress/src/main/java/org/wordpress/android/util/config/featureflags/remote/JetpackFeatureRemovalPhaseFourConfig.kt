package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import org.wordpress.android.util.config.featureflags.remote.JetpackFeatureRemovalPhaseFourConfig.Companion.JETPACK__FEATURE_REMOVAL_PHASE_FOUR_REMOTE_FIELD
import javax.inject.Inject

@RemoteFeatureFlagDefault(JETPACK__FEATURE_REMOVAL_PHASE_FOUR_REMOTE_FIELD, false)
class JetpackFeatureRemovalPhaseFourConfig @Inject constructor(
    appConfig: AppConfig
) : FeatureConfig(
    appConfig,
    BuildConfig.JETPACK_FEATURE_REMOVAL_PHASE_FOUR,
    JETPACK__FEATURE_REMOVAL_PHASE_FOUR_REMOTE_FIELD
) {
    companion object {
        const val JETPACK__FEATURE_REMOVAL_PHASE_FOUR_REMOTE_FIELD = "jp_removal_four"
    }
}
