package org.wordpress.android.util.config

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.LocalFeatureFlagDefault
import javax.inject.Inject

/**
 * Configuration for the landing screen revamp work
 */
@LocalFeatureFlagDefault
class LandingScreenRevampFeatureConfig
@Inject constructor(appConfig: AppConfig) : FeatureConfig(appConfig, BuildConfig.LANDING_SCREEN_REVAMP)
