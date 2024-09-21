package com.example.compose.navigation.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.compose.navigation.ui.list.Movie
import com.example.compose.navigation.ui.list.MovieListProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val movieListProvider: MovieListProvider
): ViewModel() {
    //region View State
    private val _movieListViewState: MutableStateFlow<MovieDetailViewState> = MutableStateFlow(
        MovieDetailViewState()
    )
    val movieDetailViewStateFlow: StateFlow<MovieDetailViewState>
        get() = _movieListViewState
    //endregion

    init {
        savedStateHandle.get<String>("movieId")?.let { selectedMovieId ->
            val selectedMovie = movieListProvider.getMovieById(selectedMovieId)
            if (selectedMovie != null) {
                onReceive(Intent.InitialState(selectedMovie))
            }
        }
    }

    fun onReceive(intent: Intent) {
        when (intent) {
            is Intent.InitialState -> {
                val initialState = MovieDetailViewState(
                    id = intent.movie.id,
                    title = intent.movie.title,
                    yearReleased = intent.movie.yearReleased.toString(),
                    genre = intent.movie.genre.name,
                    director = intent.movie.director,
                    actors = intent.movie.actors,
                    producers = intent.movie.producers
                )
                _movieListViewState.value = initialState
            }
            is Intent.InsertNewActor -> {

            }

            is Intent.SaveMovie -> {

            }

            is Intent.SetProducer -> {
                val currentState = _movieListViewState.value
                val newProducerViewState = currentState.addNewProducerViewState.copy(isExecutive = intent.isExecutive)
                _movieListViewState.value = currentState.copy(addNewProducerViewState = newProducerViewState)
            }
        }
    }
}