package com.example.compose.navigation.navigation

import com.example.compose.navigation.ui.actor.Actor
import com.example.compose.navigation.ui.producer.Producer
import kotlinx.serialization.Serializable

sealed class AppDestinations {

    @Serializable
    data object MovieList : AppDestinations()

    @Serializable
    data class MovieDetails(val movieId: String? = null) : AppDestinations()

    @Serializable
    data class AddOrEditMovie(val movieId: String? = null) : AppDestinations()

    @Serializable
    data object ActorDetail: AppDestinations()

    @Serializable
    data object ProducerDetail: AppDestinations()
}