package com.example.compose.navigation.ui.detail

data class MovieDetailViewState(
    val id: String = "",
    val title: String = "",
    val yearReleased: String? = null,
    val genre: String = "",
    val director: FullName = FullName(),
    val actors: List<FullName> = emptyList(),
    val producers: List<FullName> = emptyList()
)
