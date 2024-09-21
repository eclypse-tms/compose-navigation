package com.example.compose.navigation.ui.detail

import com.example.compose.navigation.ui.list.Movie

sealed class Intent {
    data class InitialState(val movie: Movie): Intent()
    data object SaveMovie: Intent()
    data object InsertNewActor: Intent()
    data class SetProducer(val isExecutive: Boolean): Intent()
}