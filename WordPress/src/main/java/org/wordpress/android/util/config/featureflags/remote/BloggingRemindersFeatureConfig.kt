package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureConfig
import org.wordpress.android.util.config.featureflags.remote.BloggingRemindersFeatureConfig.Companion.BLOGGING_REMINDERS_REMOTE_FIELD
import javax.inject.Inject

@RemoteFeatureFlagDefault(BLOGGING_REMINDERS_REMOTE_FIELD, true)
class BloggingRemindersFeatureConfig
@Inject constructor(appConfig: AppConfig) : FeatureConfig(
    appConfig,
    BuildConfig.BLOGGING_REMINDERS,
    BLOGGING_REMINDERS_REMOTE_FIELD
) {
    companion object {
        const val BLOGGING_REMINDERS_REMOTE_FIELD = "blogging_reminders_remote_field"
    }
}
