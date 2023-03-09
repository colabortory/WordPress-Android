package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import org.wordpress.android.util.config.featureflags.remote.JetpackInstallFullPluginFeatureFlag.Companion.JETPACK_INSTALL_FULL_PLUGIN_REMOTE_FIELD
import javax.inject.Inject

@RemoteFeatureFlagDefault(JETPACK_INSTALL_FULL_PLUGIN_REMOTE_FIELD, true)
class JetpackInstallFullPluginFeatureFlag
@Inject constructor(appConfig: AppConfig) : FeatureFlag(
    appConfig,
    BuildConfig.JETPACK_INSTALL_FULL_PLUGIN,
    JETPACK_INSTALL_FULL_PLUGIN_REMOTE_FIELD,
) {
    override fun isEnabled(): Boolean {
        return super.isEnabled() && BuildConfig.IS_JETPACK_APP
    }

    companion object {
        const val JETPACK_INSTALL_FULL_PLUGIN_REMOTE_FIELD = "jetpack_install_full_plugin_remote_field"
    }
}
