package com.example.compose.navigation.ui.actor

data class ActorDetailViewState(
    val firstName: String = "",
    val lastName: String = "",
    val dob: String = ""
) {
    fun toActor(): Actor {
        return Actor(
            firstName = firstName,
            lastName = lastName,
            dob = dob.toInt()
        )
    }
}