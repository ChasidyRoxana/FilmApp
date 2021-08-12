package com.example.filmapp.filmdetailscreen.ui

import com.example.filmapp.base.BaseViewState
import com.example.filmapp.base.Event
import com.example.filmapp.filmlistscreen.ui.model.FilmItem

data class ViewState(
    val state: State,
    val filmItem: FilmItem?,
    val error: Throwable?,
) : BaseViewState

sealed class UiEvent : Event {
    object OnNavigationBackClicked : UiEvent()
    object OnWatchButtonClicked : UiEvent()
}

sealed class DataEvent : Event {
    class LoadFilmItem(val filmItem: FilmItem) : DataEvent()
}

enum class State {
    LOAD,
    CONTENT,
    ERROR
}