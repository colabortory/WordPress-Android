package org.wordpress.android.util.config.featureflags.local

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.LocalFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import javax.inject.Inject

/**
 * Configuration for the landing screen revamp work
 */
@LocalFeatureFlagDefault
class LandingScreenRevampFeatureFlag
@Inject constructor(appConfig: AppConfig) : FeatureFlag(appConfig, BuildConfig.LANDING_SCREEN_REVAMP)
