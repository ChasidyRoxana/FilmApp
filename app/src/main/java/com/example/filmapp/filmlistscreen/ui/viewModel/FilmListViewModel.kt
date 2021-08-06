package com.example.filmapp.filmlistscreen.ui.viewModel

import com.example.filmapp.base.BaseViewModel
import com.example.filmapp.base.Event
import com.example.filmapp.filmlistscreen.DataEvent
import com.example.filmapp.filmlistscreen.FilmListViewState
import com.example.filmapp.filmlistscreen.STATUS
import com.example.filmapp.filmlistscreen.UiEvent
import com.example.filmapp.filmlistscreen.domain.interactor.FilmInteractor

class FilmListViewModel(private val filmInteractor: FilmInteractor) :
    BaseViewModel<FilmListViewState>(FilmListViewState()) {

    init {
        processUiEvent(UiEvent.OnRefreshFilms)
    }

    override suspend fun reduce(
        event: Event,
        previousState: FilmListViewState
    ): FilmListViewState? {
        when (event) {

            is UiEvent.OnRefreshFilms -> {
                processDataEvent(DataEvent.OnLoadFilms)
                filmInteractor.getFilms().fold(
                    onSuccess = { processDataEvent(DataEvent.OnSuccessFilmRequest(it)) },
                    onFailure = { processDataEvent(DataEvent.OnErrorFilmRequest(it)) }
                )
            }

            is DataEvent.OnLoadFilms -> {
                return previousState.copy(status = STATUS.LOAD)
            }

            is DataEvent.OnSuccessFilmRequest -> {
                return previousState.copy(status = STATUS.CONTENT, filmList = event.films)
            }

            is DataEvent.OnErrorFilmRequest -> {
                return previousState.copy(status = STATUS.ERROR, error = event.error)
            }
        }
        return null
    }
}