package com.example.compose.navigation.ui.detail

sealed class Intent {
    data class InitialState(val movie: MovieDetailViewState): Intent()
    data object InsertNewActor: Intent()
}