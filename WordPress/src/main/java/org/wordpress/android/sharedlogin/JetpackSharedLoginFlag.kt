package org.wordpress.android.sharedlogin

import org.wordpress.android.util.BuildConfigWrapper
import org.wordpress.android.util.config.featureflags.remote.JetpackSharedLoginFeatureFlag
import javax.inject.Inject

class JetpackSharedLoginFlag @Inject constructor(
    private val jetpackSharedLoginFeatureConfig: JetpackSharedLoginFeatureFlag,
    private val buildConfigWrapper: BuildConfigWrapper
) {
    fun isEnabled() = jetpackSharedLoginFeatureConfig.isEnabled() && buildConfigWrapper.isJetpackApp
}
