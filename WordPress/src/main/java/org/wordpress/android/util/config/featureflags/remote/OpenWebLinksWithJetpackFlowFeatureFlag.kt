package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import javax.inject.Inject

@RemoteFeatureFlagDefault(OpenWebLinksWithJetpackFlowFeatureFlag.OPEN_WEB_LINKS_WITH_JETPACK_FLOW, false)
class OpenWebLinksWithJetpackFlowFeatureFlag
@Inject constructor(appConfig: AppConfig) : FeatureFlag(
    appConfig,
    BuildConfig.OPEN_WEB_LINKS_WITH_JETPACK_FLOW,
    OPEN_WEB_LINKS_WITH_JETPACK_FLOW
) {
    override fun isEnabled(): Boolean {
        return BuildConfig.ENABLE_OPEN_WEB_LINKS_WITH_JP_FLOW && super.isEnabled()
    }

    companion object {
        const val OPEN_WEB_LINKS_WITH_JETPACK_FLOW = "open_web_links_with_jetpack_flow"
    }
}
