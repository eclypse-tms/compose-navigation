package com.example.compose.navigation.ui.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MovieListScreen(viewModel: MovieListViewModel,
                    onMovieSelected: (String) -> Unit,
                    onAddNewMovie: () -> Unit) {

    val movieListViewState: MovieListViewState by viewModel.movieListViewStateFlow.collectAsState()


    LazyColumn {
        itemsIndexed(movieListViewState.visibleMovies) { index, movie ->
            ListItem(headlineContent = {
                Text(text = movie.title)
            })
        }
    }
}

@Preview(heightDp = 1200)
@Composable
fun PreviewMovieListScreen() {

    val viewState = MovieListViewState(visibleMovies = MovieGenerator.generateTop20MovieListFromImdb())

    val viewModel = MovieListViewModel()

    viewModel.onReceive(Intent.InitialState(viewState))

    MovieListScreen(
        viewModel = viewModel,
        onMovieSelected = {},
        onAddNewMovie = {}
    )
}