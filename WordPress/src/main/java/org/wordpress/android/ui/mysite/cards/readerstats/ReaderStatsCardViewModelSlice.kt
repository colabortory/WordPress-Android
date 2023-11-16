package org.wordpress.android.ui.mysite.cards.readerstats

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import org.wordpress.android.datasets.ReaderPostTable
import org.wordpress.android.modules.BG_THREAD
import org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.ReaderStatsCard
import org.wordpress.android.ui.utils.UiString
import javax.inject.Inject
import javax.inject.Named

@Suppress("unused")
class ReaderStatsCardViewModelSlice @Inject constructor(
    @param:Named(BG_THREAD) private val bgDispatcher: CoroutineDispatcher,
) {
    private lateinit var scope: CoroutineScope

    fun initialize(scope: CoroutineScope) {
        this.scope = scope
    }

    @Suppress("MagicNumber")
    fun getCard(): ReaderStatsCard? {
        return ReaderStatsCard(
            thisWeekTime = UiString.UiStringText(getWeekReadTime()),
            thisWeekAchievement = ReaderStatsCard.Achievement.WeekTop10,
            onSiteClick = ::openSite,
            onShareClick = ::shareStats,
            mostReadSites = getWeekMostReadSites(),
        )
    }

    private fun getWeekReadTime(): String {
        val readTimeSec = ReaderPostTable.getWeekPostReadingTime()
        val readTimeMin = readTimeSec / MIN_IN_SEC
        return "$readTimeMin " + ("min".takeIf { readTimeMin == 1L } ?: "mins")
    }

    @Suppress("MagicNumber")
    private fun getWeekMostReadSites(): List<ReaderStatsCard.MostReadSite> {
        val topReadBlogs = ReaderPostTable.getWeekTopReadBlogs(3)
        return topReadBlogs
            .map {
                ReaderStatsCard.MostReadSite(
                    blogId = it.blogId,
                    blogAvatarUrl = it.blogImageUrl ?: it.authorAvatar,
                    blogName = UiString.UiStringText(it.blogName.orEmpty()),
                    blogUrl = UiString.UiStringText(it.blogUrl.orEmpty()),
                )
            }
    }

    @Suppress("UNUSED_PARAMETER")
    fun openSite(blogId: Long) {
        // TODO thomashorta
    }

    fun shareStats() {
        // TODO thomashorta
    }

    companion object {
        private const val MIN_IN_SEC = 60L
    }
}
