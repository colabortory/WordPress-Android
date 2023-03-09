package org.wordpress.android.util.config

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.LocalFeatureFlagDefault
import javax.inject.Inject

/**
 * Configuration of the Global Style Support
 */
@LocalFeatureFlagDefault
class GlobalStyleSupportFeatureConfig
@Inject constructor(appConfig: AppConfig) : FeatureConfig(appConfig, BuildConfig.GLOBAL_STYLE_SUPPORT)
