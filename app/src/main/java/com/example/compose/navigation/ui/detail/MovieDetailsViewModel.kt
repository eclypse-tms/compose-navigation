package com.example.compose.navigation.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.navigation.di.ViewModelCoroutineContext
import com.example.compose.navigation.ui.list.MovieProvider
import com.example.compose.navigation.ui.actor.ActorDetailViewState
import com.example.compose.navigation.ui.director.Director
import com.example.compose.navigation.ui.producer.Producer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    @ViewModelCoroutineContext private val couroutineContext: CoroutineContext,
    private val savedStateHandle: SavedStateHandle,
    private val movieProvider: MovieProvider
): ViewModel() {
    //region View State
    private val _movieDetailsViewState: MutableStateFlow<MovieDetailsViewState> = MutableStateFlow(
        MovieDetailsViewState()
    )
    val movieDetailsViewStateFlow: StateFlow<MovieDetailsViewState>
        get() = _movieDetailsViewState
    //endregion

    //region flows

    val producersFlow: MutableSharedFlow<Producer> = MutableSharedFlow(replay = 1)

    //endregion

    init {
        val selectedMovieId = savedStateHandle.get<String>("movieId")
        if (selectedMovieId != null) {
            val selectedMovie = movieProvider.getMovieById(selectedMovieId)
            if (selectedMovie != null) {
                onReceive(Intent.InitialState(selectedMovie))
            }
        }
        configureObservers()
    }

    private fun configureObservers() = viewModelScope.launch(couroutineContext) {
        producersFlow.distinctUntilChanged().collect {
            onReceive(Intent.AddOrEditProducer(it))
        }
    }

    fun onReceive(intent: Intent) = viewModelScope.launch(couroutineContext) {
        when (intent) {
            is Intent.InitialState -> {
                if (intent.movie != null) {
                    val initialState = MovieDetailsViewState(
                        id = intent.movie.id,
                        title = intent.movie.title,
                        yearReleased = intent.movie.yearReleased.toString(),
                        genre = intent.movie.genre.name,
                        director = intent.movie.director,
                        actors = intent.movie.actors,
                        producers = intent.movie.producers
                    )
                    _movieDetailsViewState.value = initialState
                }
            }

            is Intent.TitleChanged -> {
                val currentState = _movieDetailsViewState.value
                val newState = currentState.copy(title = intent.title)
                _movieDetailsViewState.value = newState
            }

            is Intent.GenreChanged -> {
                val currentState = _movieDetailsViewState.value
                val newState = currentState.copy(genre = intent.genre)
                _movieDetailsViewState.value = newState
            }

            is Intent.ReleaseDateChanged -> {
                val currentState = _movieDetailsViewState.value
                val newState = currentState.copy(yearReleased = intent.releaseDate)
                _movieDetailsViewState.value = newState
            }

            is Intent.DirectorFirstNameChanged -> {
                val currentState = _movieDetailsViewState.value
                val newState = currentState.copy(director = Director(firstName = intent.firstName, lastName = currentState.director.lastName))
                _movieDetailsViewState.value = newState
            }

            is Intent.DirectorLastNameChanged -> {
                val currentState = _movieDetailsViewState.value
                val newState = currentState.copy(director = Director(firstName = currentState.director.firstName, lastName = intent.lastName))
                _movieDetailsViewState.value = newState
            }

            is Intent.ActorInfoChanged -> {
                val currentState = _movieDetailsViewState.value
                val newState = currentState.copy(actorDetailViewState = intent.actor)
                _movieDetailsViewState.value = newState
            }

            is Intent.AddOrEditActor -> {
                val currentState = _movieDetailsViewState.value
                val newState = currentState.copy(actorDetailViewState = intent.actor?.toViewState() ?: ActorDetailViewState())
                _movieDetailsViewState.value = newState
            }

            is Intent.SaveActor -> {
                val currentState = _movieDetailsViewState.value
                val currentActorsList = currentState.actors.toMutableList()
                currentActorsList.removeIf { it.id == currentState.actorDetailViewState.id }
                currentActorsList.add(currentState.actorDetailViewState.toActor())
                val newState = currentState.copy(actors = currentActorsList, actorDetailViewState = ActorDetailViewState())
                _movieDetailsViewState.value = newState
            }

            is Intent.AddOrEditProducer -> {
                if (intent.producer != null) {
                    val currentState = _movieDetailsViewState.value
                    val currentProducersList = currentState.producers.toMutableList()
                    currentProducersList.removeIf { it.id == intent.producer.id }
                    currentProducersList.add(intent.producer)
                    val newState = currentState.copy(producers = currentProducersList)
                    _movieDetailsViewState.value = newState
                }
            }

            is Intent.SaveMovie -> {
                val currentState = _movieDetailsViewState.value
                movieProvider.addOrUpdate(currentState.toMovie())
            }
        }
    }
}