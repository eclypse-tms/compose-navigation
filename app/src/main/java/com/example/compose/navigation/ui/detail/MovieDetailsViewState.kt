package com.example.compose.navigation.ui.detail

import com.example.compose.navigation.ui.actor.Actor
import com.example.compose.navigation.ui.actor.ActorDetailViewState
import com.example.compose.navigation.ui.director.Director
import com.example.compose.navigation.ui.list.Movie
import com.example.compose.navigation.ui.producer.Producer


data class MovieDetailsViewState(
    val id: String = "",
    val title: String = "",
    val yearReleased: String? = null,
    val genre: String = "",
    val director: Director = Director(),
    val actors: List<Actor> = emptyList(),
    val producers: List<Producer> = emptyList(),
    val actorDetailViewState: ActorDetailViewState = ActorDetailViewState(),
) {
    fun toMovie(): Movie {
        return Movie(
            id = id,
            title = title,
            yearReleased = yearReleased?.toIntOrNull() ?: 2000,
            genre = MovieGenre.valueOf(genre),
            director = director,
            actors = actors,
            producers = producers
        )
    }
}