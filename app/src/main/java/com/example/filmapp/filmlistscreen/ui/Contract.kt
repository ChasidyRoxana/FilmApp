package com.example.filmapp.filmlistscreen.ui

import com.example.filmapp.base.BaseViewState
import com.example.filmapp.base.Event
import com.example.filmapp.filmlistscreen.ui.model.FilmItem

data class ViewState(
    val state: State,
    val filmList: List<FilmItem>,
    val error: Throwable?,
) : BaseViewState {
    val isFilmScreenLoading = state == State.LOAD
    val isEmptyErrorVisible = filmList.isEmpty() && state != State.LOAD
}

sealed class UiEvent : Event {
    object OnScreenRefreshed : UiEvent()
    class OnFilmItemClicked(val filmItem: FilmItem) : UiEvent()
}

sealed class DataEvent : Event {
    object RequestFilms : DataEvent()
    class OnFilmRequestSucceed(val films: List<FilmItem>) : DataEvent()
    class OnFilmRequestFailed(val error: Throwable) : DataEvent()
}

enum class State {
    LOAD,
    CONTENT,
    ERROR
}