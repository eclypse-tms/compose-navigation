package com.example.compose.navigation.ui.producer

sealed class Intent {
    data class InitialState(val producer: Producer?): Intent()
    data class ChangeFirstName(val firstName: String): Intent()
    data class ChangeLastName(val lastName: String): Intent()
    data class ChangeExecutive(val isExecutive: Boolean): Intent()
    data object SaveProducer: Intent()
}