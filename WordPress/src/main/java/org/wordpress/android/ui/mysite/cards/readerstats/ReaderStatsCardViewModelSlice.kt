package org.wordpress.android.ui.mysite.cards.readerstats

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import org.wordpress.android.modules.BG_THREAD
import org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.ReaderStatsCard
import org.wordpress.android.ui.utils.UiString
import javax.inject.Inject
import javax.inject.Named

class ReaderStatsCardViewModelSlice @Inject constructor(
    @param:Named(BG_THREAD) private val bgDispatcher: CoroutineDispatcher,
) {
    private lateinit var scope: CoroutineScope

    fun initialize(scope: CoroutineScope) {
        this.scope = scope
    }

    fun getCard(): ReaderStatsCard? {
        // TODO thomashorta use real data
        return ReaderStatsCard(
            thisWeekTime = UiString.UiStringText("47 mins"),
            thisWeekAchievement = ReaderStatsCard.Achievement.WeekTop10,
            onSiteClick = ::openSite,
            onShareClick = ::shareStats,
            mostReadSites = List(3) {
                ReaderStatsCard.MostReadSite(
                    blogId = 1234L,
                    blogAvatarUrl = "https://live.staticflickr.com/2746/4456420503_508cf4b0d2_b.jpg",
                    blogName = UiString.UiStringText("Culinary Wanderlust"),
                    blogUrl = UiString.UiStringText("culinarywanderlust.wordpress.com"),
                )
            }
        )
    }

    @Suppress("UNUSED_PARAMETER")
    fun openSite(blogId: Long) {
        // TODO thomashorta
    }

    fun shareStats() {
        // TODO thomashorta
    }
}
