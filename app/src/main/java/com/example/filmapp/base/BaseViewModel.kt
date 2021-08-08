package com.example.filmapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel<ViewState : BaseViewState>(
    private val initViewState: ViewState
) : ViewModel() {

    private val _viewState: MutableLiveData<ViewState> by lazy { MutableLiveData(initViewState) }
    val viewState: LiveData<ViewState>
        get() = _viewState

    abstract suspend fun reduce(event: Event, previousState: ViewState): ViewState?

    fun processUiEvent(event: Event) {
        updateState(event)
    }

    protected fun processDataEvent(event: Event) {
        updateState(event)
    }

    private fun updateState(event: Event) =
        viewModelScope.launch {
            val newViewState = reduce(event, viewState.value ?: initViewState)
            if (newViewState != null && newViewState != viewState.value) {
                _viewState.value = newViewState
            }
        }
}