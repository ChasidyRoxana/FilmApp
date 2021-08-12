package com.example.filmapp.playerscreen.ui.viewModel

import com.example.filmapp.base.BaseViewModel
import com.example.filmapp.base.Event
import com.example.filmapp.playerscreen.ui.DataEvent
import com.example.filmapp.playerscreen.ui.State
import com.example.filmapp.playerscreen.ui.ViewState

class PlayerViewModel(videoUri: String) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadVideo(videoUri))
    }

    override fun initialViewState(): ViewState =
        ViewState(state = State.LOAD, videoUri = null, error = null)

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {

            is DataEvent.LoadVideo -> {
                return previousState.copy(state = State.CONTENT, videoUri = event.videoUri)
            }

            else -> return null
        }
    }
}