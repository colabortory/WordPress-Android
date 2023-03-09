package org.wordpress.android.util.config.featureflags.local

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.LocalFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.LocalFeatureFlag
import javax.inject.Inject

/**
 * Configuration for the landing screen revamp work
 */
@LocalFeatureFlagDefault(BuildConfig.LANDING_SCREEN_REVAMP)
class LandingScreenRevampFeatureFlag
@Inject constructor(appConfig: AppConfig) : LocalFeatureFlag(appConfig)
