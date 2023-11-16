package org.wordpress.android.ui.reader.reminders

import javax.inject.Inject

class ReadEstimatedTime @Inject constructor() {
    fun inMinutes(fullText: String): Int =
        fullText.trim().split("\\s+".toRegex()).size / AVERAGE_WORDS_PER_MINUTE

    fun inMinutesFormatted(fullText: String): String {
        val minutes = inMinutes(fullText)
        return if (minutes < 1) {
            "< 1 min read"
        } else if (minutes == 1) {
            "1 min read"
        } else {
            "$minutes min read"
        }
    }
}

private const val AVERAGE_WORDS_PER_MINUTE = 238
