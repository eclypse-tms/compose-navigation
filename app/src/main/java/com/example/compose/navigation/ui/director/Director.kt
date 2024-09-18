package com.example.compose.navigation.ui.director

data class Director(val firstName: String,
                 val lastName: String,
                 val knownFor: String? = null) {
    constructor() : this("", "")

    fun displayName(): String {
        if (knownFor != null) {
            return "$firstName $lastName - $knownFor"
        } else {
            return "$firstName $lastName"
        }
    }
}
