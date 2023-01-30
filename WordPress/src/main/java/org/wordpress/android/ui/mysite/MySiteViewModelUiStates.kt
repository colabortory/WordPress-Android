package org.wordpress.android.ui.mysite

import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import org.wordpress.android.analytics.AnalyticsTracker
import org.wordpress.android.fluxc.store.QuickStartStore
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository
import org.wordpress.android.ui.mysite.tabs.MySiteTabType
import org.wordpress.android.ui.utils.UiString

data class UiModel(
    val accountAvatarUrl: String,
    val state: State
)

sealed class State {
    abstract val tabsUiState: TabsUiState
    abstract val siteInfoToolbarViewParams: SiteInfoToolbarViewParams

    data class SiteSelected(
        override val tabsUiState: TabsUiState,
        override val siteInfoToolbarViewParams: SiteInfoToolbarViewParams,
        val siteInfoHeaderState: SiteInfoHeaderState,
        val cardAndItems: List<MySiteCardAndItem>,
        val siteMenuCardsAndItems: List<MySiteCardAndItem>,
        val dashboardCardsAndItems: List<MySiteCardAndItem>
    ) : State()

    data class NoSites(
        override val tabsUiState: TabsUiState,
        override val siteInfoToolbarViewParams: SiteInfoToolbarViewParams,
        val shouldShowImage: Boolean
    ) : State()
}

data class SiteInfoHeaderState(
    val hasUpdates: Boolean,
    val siteInfoHeader: MySiteCardAndItem.SiteInfoHeaderCard
)

data class TabsUiState(
    val showTabs: Boolean = false,
    val tabUiStates: List<TabUiState>,
    val shouldUpdateViewPager: Boolean = false
) {
    data class TabUiState(
        val label: UiString,
        val tabType: MySiteTabType,
        val showQuickStartFocusPoint: Boolean = false,
        val pendingTask: QuickStartStore.QuickStartTask? = null
    )

    fun update(quickStartTabStep: QuickStartRepository.QuickStartTabStep?) = tabUiStates.map { tabUiState ->
        tabUiState.copy(
            showQuickStartFocusPoint = quickStartTabStep?.mySiteTabType == tabUiState.tabType &&
                    quickStartTabStep.isStarted,
            pendingTask = quickStartTabStep?.task
        )
    }
}

data class SiteInfoToolbarViewParams(
    @DimenRes val appBarHeight: Int,
    @DimenRes val toolbarBottomMargin: Int,
    val headerVisible: Boolean = true,
    val appBarLiftOnScroll: Boolean = false
)

data class TabNavigation(val position: Int, val smoothAnimation: Boolean)

data class TextInputDialogModel(
    val callbackId: Int = MySiteViewModel.SITE_NAME_CHANGE_CALLBACK_ID,
    @StringRes val title: Int,
    val initialText: String,
    @StringRes val hint: Int,
    val isMultiline: Boolean,
    val isInputEnabled: Boolean
)

data class SiteIdToState(val siteId: Int?, val state: MySiteUiState = MySiteUiState()) {
    fun update(partialState: MySiteUiState.PartialState): SiteIdToState {
        return this.copy(state = state.update(partialState))
    }
}

data class MySiteTrackWithTabSource(
    val stat: AnalyticsTracker.Stat,
    val properties: HashMap<String, *>? = null,
    val key: String = MySiteViewModel.TAB_SOURCE,
    val currentTab: MySiteTabType = MySiteTabType.ALL
)
