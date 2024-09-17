package com.example.compose.navigation.ui.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieListViewModel: ViewModel() {
    //region View State
    private val _movieListViewState: MutableStateFlow<MovieListViewState> = MutableStateFlow(
        MovieListViewState()
    )
    val movieListViewStateFlow: StateFlow<MovieListViewState>
        get() = _movieListViewState
    //endregion

    fun onReceive(intent: Intent) {
        when (intent) {
            is Intent.InitialState -> {
                _movieListViewState.value = intent.viewState
            }
        }
    }
}