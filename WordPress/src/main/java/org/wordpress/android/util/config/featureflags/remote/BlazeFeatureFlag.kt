package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.RemoteFeatureFlag
import javax.inject.Inject

private const val BLAZE_FEATURE_REMOTE_FIELD = "blaze"

@RemoteFeatureFlagDefault(BLAZE_FEATURE_REMOTE_FIELD, BuildConfig.ENABLE_BLAZE_FEATURE)
class BlazeFeatureFlag @Inject constructor(
    appConfig: AppConfig
) : RemoteFeatureFlag(
    appConfig,
    BLAZE_FEATURE_REMOTE_FIELD
)
