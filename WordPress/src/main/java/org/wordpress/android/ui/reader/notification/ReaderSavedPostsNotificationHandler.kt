package org.wordpress.android.ui.reader.notification

import android.app.PendingIntent
import android.content.Context
import org.wordpress.android.push.NotificationType.BLOGGING_PROMPTS_ONBOARDING
import org.wordpress.android.ui.ActivityLauncher
import org.wordpress.android.ui.jetpackoverlay.JetpackFeatureRemovalPhaseHelper
import org.wordpress.android.ui.notifications.DismissNotificationReceiver
import org.wordpress.android.workers.notification.local.LocalNotificationHandler
import javax.inject.Inject

class ReaderSavedPostsNotificationHandler @Inject constructor(
//    private val notificationsTracker: SystemNotificationsTracker,
) : LocalNotificationHandler {
    override fun shouldShowNotification(): Boolean {
        return true;
//        return accountStore.hasAccessToken() && jetpackFeatureRemovalPhaseHelper.shouldShowNotifications()
    }

    override fun buildFirstActionPendingIntent(context: Context, notificationId: Int): PendingIntent {
        val intent = ActivityLauncher.createMainActivityAndShowBloggingPromptsOnboardingActivityIntent(
            context, BLOGGING_PROMPTS_ONBOARDING, notificationId
        )
        return PendingIntent.getActivity(
            context,
            notificationId + 1,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    override fun buildSecondActionPendingIntent(context: Context, notificationId: Int): PendingIntent? {
        return PendingIntent.getBroadcast(
            context,
            notificationId + 2,
            DismissNotificationReceiver.newIntent(context, notificationId),
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    override fun onNotificationShown() {
//        notificationsTracker.trackShownNotification(BLOGGING_PROMPTS_ONBOARDING)
    }
}
