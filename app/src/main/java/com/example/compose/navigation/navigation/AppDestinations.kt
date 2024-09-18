package com.example.compose.navigation.navigation

import kotlinx.serialization.Serializable

sealed class AppDestinations {

    @Serializable
    data object MovieList : AppDestinations()

    @Serializable
    data class MovieDetail(val movieId: String? = null) : AppDestinations()

    @Serializable
    data object ActorDetail: AppDestinations()

    @Serializable
    data object ProducerDetail: AppDestinations()
}