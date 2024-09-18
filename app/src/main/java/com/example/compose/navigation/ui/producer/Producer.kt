package com.example.compose.navigation.ui.producer

data class Producer(val firstName: String,
                 val lastName: String,
                 val isExecutive: Boolean = false) {
    constructor() : this("", "")

    fun displayName(): String {
        return "$firstName $lastName"
    }
}