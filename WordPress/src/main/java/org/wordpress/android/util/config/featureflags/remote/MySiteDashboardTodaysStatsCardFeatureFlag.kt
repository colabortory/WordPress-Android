package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import org.wordpress.android.util.config.featureflags.remote.MySiteDashboardTodaysStatsCardFeatureFlag.Companion.MY_SITE_DASHBOARD_TODAYS_STATS_CARD
import javax.inject.Inject

/**
 * Configuration of the 'My Site Dashboard - Today's Stats Card' that will add the card on the 'My Site' screen.
 */
@RemoteFeatureFlagDefault(
    remoteField = MY_SITE_DASHBOARD_TODAYS_STATS_CARD,
    defaultValue = true
)
class MySiteDashboardTodaysStatsCardFeatureFlag @Inject constructor(
    appConfig: AppConfig
) : FeatureFlag(
    appConfig,
    BuildConfig.MY_SITE_DASHBOARD_TODAYS_STATS_CARD,
    MY_SITE_DASHBOARD_TODAYS_STATS_CARD
) {
    companion object {
        const val MY_SITE_DASHBOARD_TODAYS_STATS_CARD = "my_site_dashboard_todays_stats_card"
    }
}
