package org.wordpress.android.ui.reader.reminders

import javax.inject.Inject

class ReadEstimatedTime @Inject constructor() {
    fun inSeconds(text: String): Int =
        text.trim().split("\\s+".toRegex()).size / AVERAGE_WORDS_PER_SECOND
}

private const val AVERAGE_WORDS_PER_SECOND = 4
