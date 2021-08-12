package com.example.filmapp.playerscreen.ui

import com.example.filmapp.base.BaseViewState
import com.example.filmapp.base.Event

data class ViewState(
    val state: State,
    val videoUri: String?,
    val error: Throwable?
) : BaseViewState

sealed class UiEvent : Event

sealed class DataEvent : Event {
    class LoadVideo(val videoUri: String?) : DataEvent()
}

enum class State {
    LOAD,
    CONTENT,
    ERROR
}