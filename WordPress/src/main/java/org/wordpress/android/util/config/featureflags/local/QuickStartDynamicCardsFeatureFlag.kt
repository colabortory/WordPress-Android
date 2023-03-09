package org.wordpress.android.util.config.featureflags.local

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.LocalFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import javax.inject.Inject

/**
 * Configuration of the Quick Start Dynamic cards within the My Site improvements
 */
@LocalFeatureFlagDefault
class QuickStartDynamicCardsFeatureFlag
@Inject constructor(
    appConfig: AppConfig
) : FeatureFlag(
    appConfig,
    BuildConfig.QUICK_START_DYNAMIC_CARDS
)
