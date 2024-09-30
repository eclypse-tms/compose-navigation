package com.example.compose.navigation.ui.detail

import com.example.compose.navigation.ui.actor.Actor
import com.example.compose.navigation.ui.list.Movie
import com.example.compose.navigation.ui.producer.Producer

sealed class Intent {
    data class InitialState(val movie: Movie? = null): Intent()
    data object SaveMovie: Intent()
    data class AddOrEditActor(val actor: Actor? = null): Intent()
    data class AddOrEditProducer(val producer: Producer? = null): Intent()
}