package org.wordpress.android.util.config.featureflags.local

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.LocalFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.LocalFeatureFlag
import javax.inject.Inject

/**
 * Configuration of the Global Style Support
 */
@LocalFeatureFlagDefault(BuildConfig.GLOBAL_STYLE_SUPPORT)
class GlobalStyleSupportFeatureFlag
@Inject constructor(appConfig: AppConfig) : LocalFeatureFlag(appConfig)
