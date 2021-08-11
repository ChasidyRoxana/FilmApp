package com.example.filmapp.filmlistscreen.ui.viewModel

import com.example.filmapp.Screens.filmDetailScreen
import com.example.filmapp.base.BaseViewModel
import com.example.filmapp.base.Event
import com.example.filmapp.filmlistscreen.domain.interactor.FilmInteractor
import com.example.filmapp.filmlistscreen.ui.DataEvent
import com.example.filmapp.filmlistscreen.ui.State
import com.example.filmapp.filmlistscreen.ui.UiEvent
import com.example.filmapp.filmlistscreen.ui.ViewState
import com.github.terrakok.cicerone.Router

class FilmListViewModel(
    private val filmInteractor: FilmInteractor,
    private val router: Router
) : BaseViewModel<ViewState>() {

    init {
        processUiEvent(UiEvent.OnScreenRefreshed)
    }

    override fun initialViewState(): ViewState =
        ViewState(state = State.LOAD, filmList = emptyList(), error = null)

    override suspend fun reduce(
        event: Event,
        previousState: ViewState
    ): ViewState? {
        when (event) {

            is UiEvent.OnScreenRefreshed -> {
                processDataEvent(DataEvent.RequestFilms)
                filmInteractor.getFilms().fold(
                    onSuccess = { processDataEvent(DataEvent.OnFilmRequestSucceed(it)) },
                    onFailure = { processDataEvent(DataEvent.OnFilmRequestFailed(it)) }
                )
                return null
            }

            is UiEvent.OnFilmItemClicked -> {
                router.navigateTo(filmDetailScreen(event.filmItem))
                return null
            }

            is DataEvent.RequestFilms -> {
                return previousState.copy(state = State.LOAD)
            }

            is DataEvent.OnFilmRequestSucceed -> {
                return previousState.copy(state = State.CONTENT, filmList = event.films)
            }

            is DataEvent.OnFilmRequestFailed -> {
                return previousState.copy(state = State.ERROR, error = event.error)
            }

            else -> return null
        }
    }
}