package com.example.compose.navigation.ui.detail

import com.example.compose.navigation.ui.actor.Actor
import com.example.compose.navigation.ui.director.Director
import com.example.compose.navigation.ui.producer.Producer

data class MovieDetailViewState(
    val id: String = "",
    val title: String = "",
    val yearReleased: String? = null,
    val genre: String = "",
    val director: Director = Director(),
    val actors: List<Actor> = emptyList(),
    val producers: List<Producer> = emptyList(),
    val addNewActorViewState: AddNewActorViewState = AddNewActorViewState(),
    val addNewProducerViewState: AddNewProducerViewState = AddNewProducerViewState()
)

data class AddNewActorViewState(
    val firstName: String = "",
    val lastName: String = "",
    val dob: String = ""
) {
    fun toActor(): Actor {
        return Actor(firstName = firstName,
            lastName = lastName,
            dob = dob.toInt()
        )
    }
}

data class AddNewProducerViewState(
    val firstName: String = "",
    val lastName: String = "",
    val isExecutive: Boolean = false
)
