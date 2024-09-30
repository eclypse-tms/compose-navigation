package com.example.compose.navigation.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.navigation.di.ViewModelCoroutineContext
import com.example.compose.navigation.ui.list.MovieListProvider
import com.example.compose.navigation.ui.actor.ActorDetailViewState
import com.example.compose.navigation.ui.producer.MovieDetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    @ViewModelCoroutineContext private val couroutineContext: CoroutineContext,
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

    fun onReceive(intent: Intent) = viewModelScope.launch(couroutineContext) {
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
            is Intent.AddOrEditActor -> {
                val currentState = _movieListViewState.value
                val newState = currentState.copy(actorDetailViewState = intent.actor?.toAddNewActorViewState() ?: ActorDetailViewState())
                _movieListViewState.value = newState
            }

            is Intent.AddOrEditProducer -> {

            }

            is Intent.SaveMovie -> {

            }

            is Intent.SetProducer -> {

            }
        }
    }
}