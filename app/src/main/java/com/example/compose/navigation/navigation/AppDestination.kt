package com.example.compose.navigation.navigation

import com.example.compose.navigation.ui.actor.Actor
import com.example.compose.navigation.ui.producer.Producer
import kotlinx.serialization.Serializable

sealed class AppDestination {

    @Serializable
    data object MovieList: AppDestination()

    @Serializable
    data object MovieDetails: AppDestination()

    @Serializable
    data class EditMovie(val movieId: String? = null): AppDestination()

    @Serializable
    data class ActorDetails(val actor: Actor? = null): AppDestination()

    @Serializable
    data class ProduceDetails(val producer: Producer? = null): AppDestination()
}