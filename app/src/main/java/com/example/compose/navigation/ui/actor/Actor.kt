package com.example.compose.navigation.ui.actor

data class Actor(val firstName: String,
                 val lastName: String,
                 val dob: Int? = null) {
    constructor() : this("", "")

    fun displayName(): String {
        if (dob != null) {
            return "$firstName $lastName - $dob"
        } else {
            return "$firstName $lastName"
        }
    }
}
