package com.example.compose.navigation.ui.producer

import com.example.compose.navigation.ui.actor.Actor
import com.example.compose.navigation.ui.actor.ActorDetailViewState
import com.example.compose.navigation.ui.director.Director

data class MovieDetailViewState(
    val id: String = "",
    val title: String = "",
    val yearReleased: String? = null,
    val genre: String = "",
    val director: Director = Director(),
    val actors: List<Actor> = emptyList(),
    val producers: List<Producer> = emptyList(),
    val actorDetailViewState: ActorDetailViewState = ActorDetailViewState(),
)