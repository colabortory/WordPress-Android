package org.wordpress.android.reader.savedposts

import org.wordpress.android.util.BuildConfigWrapper
import org.wordpress.android.util.config.featureflags.remote.JetpackReaderSavedPostsFeatureFlag
import javax.inject.Inject

class JetpackReaderSavedPostsFlag @Inject constructor(
    private val jetpackReaderSavedPostsFeatureConfig: JetpackReaderSavedPostsFeatureFlag,
    private val buildConfigWrapper: BuildConfigWrapper
) {
    fun isEnabled() = jetpackReaderSavedPostsFeatureConfig.isEnabled() && buildConfigWrapper.isJetpackApp
}
