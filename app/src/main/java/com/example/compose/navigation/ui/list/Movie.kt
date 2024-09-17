package com.example.compose.navigation.ui.list

import com.example.compose.navigation.ui.detail.FullName
import com.example.compose.navigation.ui.detail.MovieGenre

data class Movie(val id: String,
                 val title: String,
                 val yearReleased: Int,
                 val genre: MovieGenre,
                 val director: FullName,
                 val actors: List<FullName>,
                 val producers: List<FullName>)
