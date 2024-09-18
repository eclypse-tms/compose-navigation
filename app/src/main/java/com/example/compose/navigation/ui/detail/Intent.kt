package com.example.compose.navigation.ui.detail

import com.example.compose.navigation.ui.producer.Producer

sealed class Intent {
    data class InitialState(val movie: MovieDetailViewState): Intent()
    data object InsertNewActor: Intent()
    data class SetProducer(val isExecutive: Boolean): Intent()
}