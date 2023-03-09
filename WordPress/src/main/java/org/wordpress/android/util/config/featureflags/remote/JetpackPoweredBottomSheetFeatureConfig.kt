package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import javax.inject.Inject

@RemoteFeatureFlagDefault(JetpackPoweredBottomSheetFeatureConfig.JETPACK_POWERED_BOTTOM_SHEET_REMOTE_FIELD, false)
class JetpackPoweredBottomSheetFeatureConfig @Inject constructor(
    appConfig: AppConfig
) : FeatureConfig(
    appConfig,
    BuildConfig.JETPACK_POWERED_BOTTOM_SHEET,
    JETPACK_POWERED_BOTTOM_SHEET_REMOTE_FIELD
) {
    companion object {
        const val JETPACK_POWERED_BOTTOM_SHEET_REMOTE_FIELD = "jetpack_powered_bottom_sheet_remote_field"
    }
}
