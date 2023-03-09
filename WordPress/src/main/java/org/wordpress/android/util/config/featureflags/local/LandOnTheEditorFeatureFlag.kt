package org.wordpress.android.util.config.featureflags.local

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.LocalFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.LocalFeatureFlag
import javax.inject.Inject

/**
 * Configuration for landing on the editor at the end of the Site Creation flow
 */
@LocalFeatureFlagDefault(BuildConfig.LAND_ON_THE_EDITOR)
class LandOnTheEditorFeatureFlag
@Inject constructor(appConfig: AppConfig) : LocalFeatureFlag(
    appConfig
)
