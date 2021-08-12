package com.example.filmapp.filmdetailscreen.ui.viewModel

import com.example.filmapp.Screens.playerScreen
import com.example.filmapp.base.BaseViewModel
import com.example.filmapp.base.Event
import com.example.filmapp.filmdetailscreen.ui.DataEvent
import com.example.filmapp.filmdetailscreen.ui.State
import com.example.filmapp.filmdetailscreen.ui.UiEvent
import com.example.filmapp.filmdetailscreen.ui.ViewState
import com.example.filmapp.filmlistscreen.ui.model.FilmItem
import com.github.terrakok.cicerone.Router

class FilmDetailViewModel(private val router: Router, filmItem: FilmItem) :
    BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadFilmItem(filmItem))
    }

    override fun initialViewState(): ViewState =
        ViewState(state = State.LOAD, filmItem = null, null)

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {

            is UiEvent.OnNavigationBackClicked -> {
                router.exit()
                return null
            }

            is UiEvent.OnWatchButtonClicked -> {
                val videoUri = previousState.filmItem!!.videoUrl
                router.navigateTo(playerScreen(videoUri))
                return null
            }

            is DataEvent.LoadFilmItem -> {
                return previousState.copy(state = State.CONTENT, filmItem = event.filmItem)
            }

            else -> return null
        }
    }
}