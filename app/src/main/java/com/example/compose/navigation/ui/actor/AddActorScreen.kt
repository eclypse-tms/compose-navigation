package com.example.compose.navigation.ui.actor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import com.example.compose.navigation.ui.detail.MovieDetailViewModel
import com.example.compose.navigation.ui.list.MovieGenerator
import com.example.compose.navigation.ui.list.MovieListProviderImpl
import com.example.compose.navigation.ui.theme.WayfinderTheme
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddActorScreen(viewModel: MovieDetailViewModel,
                   onDismissScreen: () -> Unit) {

    val currentMovieDetail by viewModel.movieDetailViewStateFlow.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Actor Information")
            }, navigationIcon = {
                IconButton(onClick = onDismissScreen) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            })
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)
            .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                placeholder = {
                    Text(text = "First Name")
                },
                value = currentMovieDetail.actorDetailViewState.firstName,
                onValueChange = {

                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )


            TextField(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                placeholder = {
                    Text(text = "Last Name")
                },
                value = currentMovieDetail.actorDetailViewState.lastName,
                onValueChange = {

                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )

            TextField(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                placeholder = {
                    Text(text = "Date of Birth")
                },
                value = currentMovieDetail.actorDetailViewState.dob,
                onValueChange = {

                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            Button(modifier = Modifier.widthIn(min = 150.dp),
                onClick = {

            }) {
                Text(text = "Save")
            }

        }
    }
}

@Preview
@Composable
fun PreviewAddActorScreen() {
    val previewViewModel = MovieDetailViewModel(
        couroutineContext = Dispatchers.Main,
        savedStateHandle = SavedStateHandle(),
        movieListProvider = MovieListProviderImpl(MovieGenerator())
    )
    WayfinderTheme {
        AddActorScreen(viewModel = previewViewModel,
            onDismissScreen = {})
    }
}