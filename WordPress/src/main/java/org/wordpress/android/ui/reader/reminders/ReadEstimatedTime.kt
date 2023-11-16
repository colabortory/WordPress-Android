package org.wordpress.android.ui.reader.reminders

class ReadEstimatedTime {
    fun wordsPerMinute(fullText: String): Int =
        fullText.trim().split("\\s+".toRegex()).size / AVERAGE_WORDS_PER_MINUTE
}

private const val AVERAGE_WORDS_PER_MINUTE = 238
