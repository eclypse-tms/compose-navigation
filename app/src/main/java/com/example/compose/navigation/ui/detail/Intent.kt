package com.example.compose.navigation.ui.detail

import com.example.compose.navigation.ui.actor.Actor
import com.example.compose.navigation.ui.actor.ActorDetailViewState
import com.example.compose.navigation.ui.list.Movie
import com.example.compose.navigation.ui.producer.Producer

sealed class Intent {
    data class InitialState(val movie: Movie? = null): Intent()

    // movie title, release date and genre, director
    data class TitleChanged(val title: String): Intent()
    data class ReleaseDateChanged(val releaseDate: String): Intent()
    data class GenreChanged(val genre: String): Intent()
    data class DirectorFirstNameChanged(val firstName: String): Intent()
    data class DirectorLastNameChanged(val lastName: String): Intent()

    // add or save actor
    data class AddOrEditActor(val actor: Actor? = null): Intent()
    data class ActorInfoChanged(val actor: ActorDetailViewState): Intent()
    data object SaveActor: Intent()

    // producer info
    data class AddOrEditProducer(val producer: Producer? = null): Intent()

    // saves movie
    data object SaveMovie: Intent()
}