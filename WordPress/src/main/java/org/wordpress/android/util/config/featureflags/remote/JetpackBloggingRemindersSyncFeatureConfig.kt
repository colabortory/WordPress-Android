package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import org.wordpress.android.util.config.featureflags.remote.JetpackBloggingRemindersSyncFeatureConfig.Companion.JETPACK_BLOGGING_REMINDERS_SYNC_REMOTE_FIELD
import javax.inject.Inject

@RemoteFeatureFlagDefault(JETPACK_BLOGGING_REMINDERS_SYNC_REMOTE_FIELD, false)
class JetpackBloggingRemindersSyncFeatureConfig
@Inject constructor(
    appConfig: AppConfig
) : FeatureConfig(
    appConfig,
    BuildConfig.JETPACK_BLOGGING_REMINDERS_SYNC,
    JETPACK_BLOGGING_REMINDERS_SYNC_REMOTE_FIELD
) {
    companion object {
        const val JETPACK_BLOGGING_REMINDERS_SYNC_REMOTE_FIELD = "jetpack_blogging_reminders_sync_remote_field"
    }
}
