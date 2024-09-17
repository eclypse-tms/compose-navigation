package com.example.compose.navigation.ui.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieDetailViewModel: ViewModel() {
    //region View State
    private val _movieListViewState: MutableStateFlow<MovieDetailViewState> = MutableStateFlow(
        MovieDetailViewState()
    )
    val movieDetailViewStateFlow: StateFlow<MovieDetailViewState>
        get() = _movieListViewState
    //endregion

    fun onReceive(intent: Intent) {
        when (intent) {
            is Intent.InitialState -> {
                _movieListViewState.value = intent.movie
            }
            is Intent.InsertNewActor -> {

            }
        }
    }
}