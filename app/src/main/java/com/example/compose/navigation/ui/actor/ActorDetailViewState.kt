package com.example.compose.navigation.ui.actor

data class ActorDetailViewState(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val dob: String = ""
) {
    fun toActor(): Actor {
        return Actor(
            id = id,
            firstName = firstName,
            lastName = lastName,
            dob = dob.toIntOrNull()
        )
    }
}