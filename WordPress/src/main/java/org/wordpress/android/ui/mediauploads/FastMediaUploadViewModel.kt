package org.wordpress.android.ui.mediauploads

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.wordpress.android.modules.UI_THREAD
import org.wordpress.android.viewmodel.ScopedViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class FastMediaUploadViewModel @Inject constructor(
    @Named(UI_THREAD) private val mainDispatcher: CoroutineDispatcher,
) : ScopedViewModel(mainDispatcher) {
    private val _actionEvents = MutableSharedFlow<ActionEvent>()
    val actionEvents: Flow<ActionEvent> = _actionEvents

    fun onCloseTapped() {
        launch {
            _actionEvents.emit(ActionEvent.CloseScreen)
        }
    }

    fun onPickMediaTapped() {
        launch {
            _actionEvents.emit(ActionEvent.OpenMediaPicker)
        }
    }

    sealed class ActionEvent {
        data object CloseScreen : ActionEvent()
        data object OpenMediaPicker: ActionEvent()
    }
}
