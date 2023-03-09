package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig.SEEN_UNSEEN_WITH_COUNTER
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import org.wordpress.android.util.config.featureflags.remote.SeenUnseenWithCounterFeatureFlag.Companion.SEEN_UNSEEN_WITH_COUNTER_REMOTE_FIELD
import javax.inject.Inject

/**
 * Configuration of the Unread Posts Count and Seen Status Toggle
 */
@RemoteFeatureFlagDefault(SEEN_UNSEEN_WITH_COUNTER_REMOTE_FIELD, true)
class SeenUnseenWithCounterFeatureFlag
@Inject constructor(appConfig: AppConfig) : FeatureFlag(
    appConfig,
    SEEN_UNSEEN_WITH_COUNTER,
    SEEN_UNSEEN_WITH_COUNTER_REMOTE_FIELD
) {
    companion object {
        const val SEEN_UNSEEN_WITH_COUNTER_REMOTE_FIELD = "seen_unseen_with_counter"
    }
}
