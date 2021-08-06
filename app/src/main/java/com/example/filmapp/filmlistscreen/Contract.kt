package com.example.filmapp.filmlistscreen

import com.example.filmapp.base.BaseViewState
import com.example.filmapp.base.Event
import com.example.filmapp.filmlistscreen.ui.model.FilmUi

data class FilmListViewState(
    val status: STATUS = STATUS.LOAD,
    val filmList: List<FilmUi> = emptyList(),
    val error: Throwable? = null,
) : BaseViewState {
    val isEmptyErrorVisible
        get() = filmList.isEmpty() && status != STATUS.LOAD
}

sealed class UiEvent : Event {
    object OnRefreshFilms : UiEvent()
}

sealed class DataEvent : Event {
    object OnLoadFilms : DataEvent()
    class OnSuccessFilmRequest(val films: List<FilmUi>) : DataEvent()
    class OnErrorFilmRequest(val error: Throwable) : DataEvent()
}

enum class STATUS {
    LOAD,
    CONTENT,
    ERROR
}