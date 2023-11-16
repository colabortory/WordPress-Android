package org.wordpress.android.ui.reader.reminders

import javax.inject.Inject

class ReadTimeFormatter @Inject constructor() {
    fun minutes(minutes: Int): String {
        return if (minutes < 1) {
            "< 1 min read"
        } else if (minutes == 1) {
            "1 min read"
        } else {
            "$minutes min read"
        }
    }
}
