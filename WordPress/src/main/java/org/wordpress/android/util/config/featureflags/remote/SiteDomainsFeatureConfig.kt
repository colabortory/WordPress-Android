package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import javax.inject.Inject

@RemoteFeatureFlagDefault(SiteDomainsFeatureConfig.SITE_DOMAINS_REMOTE_FIELD, true)
class SiteDomainsFeatureConfig
@Inject constructor(appConfig: AppConfig) : FeatureConfig(
    appConfig,
    BuildConfig.SITE_DOMAINS,
    SITE_DOMAINS_REMOTE_FIELD
) {
    companion object {
        const val SITE_DOMAINS_REMOTE_FIELD = "site_domains_remote_field"
    }
}
