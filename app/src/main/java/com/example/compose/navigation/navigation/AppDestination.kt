package com.example.compose.navigation.navigation

import com.example.compose.navigation.ui.producer.Producer
import kotlinx.serialization.Serializable

sealed class AppDestination {

    @Serializable
    data object MovieList: AppDestination()

    @Serializable
    data object MovieDetailsParent: AppDestination()

    @Serializable
    data class MovieDetails(val movieId: String? = null): AppDestination()

    @Serializable
    data object ActorDetails: AppDestination()

    @Serializable
    data class ProducerDetails(val producer: Producer? = null): AppDestination()
}