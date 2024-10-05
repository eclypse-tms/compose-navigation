package com.example.compose.navigation.ui.list

sealed class Intent {
    data class InitialState(val existingMovies: List<Movie>) : Intent()
}