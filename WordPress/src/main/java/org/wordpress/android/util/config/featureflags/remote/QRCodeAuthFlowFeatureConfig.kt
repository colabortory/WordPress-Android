package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import javax.inject.Inject

@RemoteFeatureFlagDefault(QRCodeAuthFlowFeatureConfig.QRCODE_AUTH_FLOW_REMOTE_FIELD, true)
class QRCodeAuthFlowFeatureConfig
@Inject constructor(appConfig: AppConfig) : FeatureConfig(
    appConfig,
    BuildConfig.QRCODE_AUTH_FLOW,
    QRCODE_AUTH_FLOW_REMOTE_FIELD
) {
    override fun isEnabled(): Boolean {
        return super.isEnabled() && BuildConfig.ENABLE_QRCODE_AUTH_FLOW
    }

    companion object {
        const val QRCODE_AUTH_FLOW_REMOTE_FIELD = "qrcode_auth_flow_remote_field"
    }
}
