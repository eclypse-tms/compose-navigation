package com.example.compose.navigation.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.navigation.ui.theme.WayfinderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(viewModel: MovieListViewModel,
                    onMovieSelected: (String) -> Unit,
                    onAddNewMovie: () -> Unit) {

    val movieListViewState: MovieListViewState by viewModel.movieListViewStateFlow.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Movies")
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddNewMovie,
            ) {
                Icon(Icons.Filled.Add, null)
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            itemsIndexed(movieListViewState.visibleMovies) { index, movie ->
                ListItem(modifier = Modifier.clickable {
                     onMovieSelected(movie.id)
               },
                    headlineContent = {
                    Text(text = movie.title)
                },
                    shadowElevation = 4.dp)
            }
        }
    }
}

@Preview(heightDp = 800)
@Composable
fun PreviewMovieListScreen() {

    val movieGenerator = MovieGenerator()
    val movieListProvider = MovieListProviderImpl(movieGenerator)
    val viewModel = MovieListViewModel(movieListProvider)

    viewModel.onReceive(Intent.InitialState(movieGenerator.generateTop3MovieListFromImdb()))

    WayfinderTheme {
        MovieListScreen(
            viewModel = viewModel,
            onMovieSelected = {},
            onAddNewMovie = {}
        )
    }
}