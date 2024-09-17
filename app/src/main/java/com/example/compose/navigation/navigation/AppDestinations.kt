package com.example.compose.navigation.navigation

sealed class AppDestinations {
    data object MovieList : AppDestinations()
    data class MovieDetail(val movieId: String? = null) : AppDestinations()
}