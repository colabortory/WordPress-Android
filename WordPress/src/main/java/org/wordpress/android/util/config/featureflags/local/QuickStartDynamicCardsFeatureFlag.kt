package org.wordpress.android.util.config.featureflags.local

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.LocalFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.LocalFeatureFlag
import javax.inject.Inject

/**
 * Configuration of the Quick Start Dynamic cards within the My Site improvements
 */
@LocalFeatureFlagDefault(BuildConfig.QUICK_START_DYNAMIC_CARDS)
class QuickStartDynamicCardsFeatureFlag
@Inject constructor(
    appConfig: AppConfig
) : LocalFeatureFlag(
    appConfig
)
