package com.example.filmapp.filmdetailscreen.ui.viewModel

import com.example.filmapp.base.BaseViewModel
import com.example.filmapp.base.Event
import com.example.filmapp.filmdetailscreen.ui.State
import com.example.filmapp.filmdetailscreen.ui.UiEvent
import com.example.filmapp.filmdetailscreen.ui.ViewState
import com.example.filmapp.filmlistscreen.ui.model.FilmItem
import com.github.terrakok.cicerone.Router

class FilmDetailViewModel(private val router: Router, private val filmItem: FilmItem) :
    BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState =
        ViewState(state = State.CONTENT, filmItem = filmItem, null)

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {

            is UiEvent.OnNavigationBackClicked -> {
                router.exit()
                return null
            }

            is UiEvent.OnWatchButtonClicked -> {
                return null
            }

            else -> return null
        }
    }
}