package org.wordpress.android.ui.reader.notification

import org.wordpress.android.R
import org.wordpress.android.workers.notification.local.LocalNotification
import org.wordpress.android.workers.notification.local.LocalNotification.Type.READER_SAVED_POSTS
import org.wordpress.android.workers.notification.local.LocalNotificationScheduler
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

class ReaderSavedPostsNotificationScheduler @Inject constructor(
    private val readerSavedPostsNotificationHandler: ReaderSavedPostsNotificationHandler,
    private val localNotificationScheduler: LocalNotificationScheduler,
) {
    fun scheduleReaderSavedPostsNotificationIfNeeded() {
        if (readerSavedPostsNotificationHandler.shouldShowNotification()) {
            val notification = LocalNotification(
                type = READER_SAVED_POSTS,
                delay = 3000,
                delayUnits = MILLISECONDS,
                title = R.string.reader_saved_posts_notification_title,
                text = R.string.reader_saved_posts_notification_text,
                icon = R.drawable.ic_wordpress_white_24dp,
                firstActionIcon = -1,
                firstActionTitle = R.string.reader_saved_posts_notification_action,
                secondActionIcon = -1,
                secondActionTitle = R.string.reader_saved_posts_notification_dismiss
            )
            localNotificationScheduler.scheduleOneTimeNotification(notification)
        }
    }
}
