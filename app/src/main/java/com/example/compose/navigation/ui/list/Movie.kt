package com.example.compose.navigation.ui.list

import com.example.compose.navigation.ui.actor.Actor
import com.example.compose.navigation.ui.detail.MovieGenre
import com.example.compose.navigation.ui.director.Director
import com.example.compose.navigation.ui.producer.Producer

data class Movie(val id: String,
                 val title: String,
                 val yearReleased: Int,
                 val genre: MovieGenre,
                 val director: Director,
                 val actors: List<Actor>,
                 val producers: List<Producer>)
