package org.wordpress.android.util.config.featureflags.local

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.LocalFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.LocalFeatureFlag
import javax.inject.Inject

/**
 * Configuration of the Site Name step in the Site Creation flow
 */
@LocalFeatureFlagDefault(BuildConfig.BETA_SITE_DESIGNS)
class BetaSiteDesignsFeatureFlag
@Inject constructor(appConfig: AppConfig) : LocalFeatureFlag(appConfig)
