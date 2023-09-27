package org.wordpress.android.ui.mysite.personalization

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import org.wordpress.android.modules.BG_THREAD
import org.wordpress.android.ui.mysite.SelectedSiteRepository
import org.wordpress.android.viewmodel.ScopedViewModel
import javax.inject.Inject
import javax.inject.Named

const val CARD_TYPE_TRACK_PARAM = "type"

@HiltViewModel
class PersonalizationViewModel @Inject constructor(
    @param:Named(BG_THREAD) private val bgDispatcher: CoroutineDispatcher,
    private val selectedSiteRepository: SelectedSiteRepository,
    private val shortcutsPersonalizationViewModelSliceImpl: ShortcutsPersonalizationViewModelSliceImpl,
    private val dashboardCardPersonalizationViewModelSliceImpl: DashboardCardPersonalizationViewModelSliceImpl,
    private val delegateManager: ViewModelSliceDelegateManager
) : ScopedViewModel(bgDispatcher),
    ShortcutsPersonalizationViewModelSlice by shortcutsPersonalizationViewModelSliceImpl,
    DashboardCardPersonalizationViewModelSlice by dashboardCardPersonalizationViewModelSliceImpl
{
    init {
        delegateManager.addDelegate(shortcutsPersonalizationViewModelSliceImpl)
        delegateManager.addDelegate(dashboardCardPersonalizationViewModelSliceImpl)
        delegateManager.initialize(viewModelScope)
    }

    fun start() {
        val siteId = selectedSiteRepository.getSelectedSite()!!.siteId
        start(siteId)
        getShortcutsPersonalization(selectedSiteRepository.getSelectedSite()!!)
    }

    override fun onCleared() {
        super.onCleared()
        delegateManager.onCleared()
    }
}

class ViewModelSliceDelegateManager @Inject constructor() {
    private val delegates = mutableListOf<ViewModelSlice>()

    fun addDelegate(delegate: ViewModelSlice) {
        delegates.add(delegate)
    }

    fun initialize(viewModelScope: CoroutineScope) {
        delegates.forEach { it.initialize(viewModelScope) }
    }

    fun onCleared() {
        delegates.forEach { it.onCleared() }
    }
}
