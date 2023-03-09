package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import org.wordpress.android.util.config.featureflags.remote.MySiteDashboardTabsFeatureConfig.Companion.MY_SITE_DASHBOARD_TABS
import javax.inject.Inject

/**
 * Configuration of the 'My Site Dashboard - Tabs' that will display tabs on the 'My Site' screen.
 */
@RemoteFeatureFlagDefault(
    remoteField = MY_SITE_DASHBOARD_TABS,
    defaultValue = true
)
class MySiteDashboardTabsFeatureConfig @Inject constructor(
    appConfig: AppConfig
) : FeatureConfig(
    appConfig,
    BuildConfig.MY_SITE_DASHBOARD_TABS,
    MY_SITE_DASHBOARD_TABS
) {
    companion object {
        const val MY_SITE_DASHBOARD_TABS = "my_site_dashboard_tabs"
    }
}
