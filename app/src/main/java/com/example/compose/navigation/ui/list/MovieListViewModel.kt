package com.example.compose.navigation.ui.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListProvider: MovieListProvider
): ViewModel() {
    //region View State
    private val _movieListViewState: MutableStateFlow<MovieListViewState> = MutableStateFlow(
        MovieListViewState()
    )
    val movieListViewStateFlow: StateFlow<MovieListViewState>
        get() = _movieListViewState
    //endregion

    init {
        onReceive(Intent.InitialState(movieListProvider.getMovies()))
    }

    fun onReceive(intent: Intent) {
        when (intent) {
            is Intent.InitialState -> {
                _movieListViewState.value = _movieListViewState.value.copy(intent.existingMovies)
            }
        }
    }
}