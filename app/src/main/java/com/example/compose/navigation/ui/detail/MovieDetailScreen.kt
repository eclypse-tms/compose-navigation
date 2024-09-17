package com.example.compose.navigation.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.navigation.ui.theme.NavyBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(viewModel: MovieDetailViewModel,
                      onDismissScreen: () -> Unit) {

    val movieDetails: MovieDetailViewState by viewModel.movieDetailViewStateFlow.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var currentSelectedGenre by remember { mutableStateOf(movieDetails.genre) }


    Scaffold(modifier = Modifier,
        topBar = {
            TopAppBar(title = {
            Text(text = "Movie Details") })
    }, bottomBar = {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically) {
            Button(colors = ButtonColors(
                containerColor = Color.Transparent,
                contentColor = NavyBlue,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.Transparent,
            ),
                onClick = {
                onDismissScreen()
            }) {
                Text(text = "Cancel")
            }

            Button(onClick = {

            }) {
                Text(text = "Save")
            }
        }
    }) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start) {
            // Title
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                placeholder = {
                    Text(text = "Title")
                },
                value = movieDetails.title,
                onValueChange = {

                })

            // release date
            val movieReleaseDate: String = movieDetails.yearReleased ?: ""
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                placeholder = {
                    Text(text = "Release Date")
                },
                value = movieReleaseDate,
                onValueChange = {

                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            // genre selection
            ExposedDropdownMenuBox(
                modifier = Modifier.padding(bottom = 8.dp),
                expanded = expanded,
                onExpandedChange = { expanded = it },
            ) {
                TextField(
                    // The `menuAnchor` modifier must be passed to the text field to handle
                    // expanding/collapsing the menu on click. A read-only text field has
                    // the anchor type `PrimaryNotEditable`.
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth(),
                    value = currentSelectedGenre,
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true,
                    placeholder = {
                        Text("Genre")
                    },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    MovieGenre.entries.forEach { eachMovieGenre ->
                        DropdownMenuItem(
                            modifier = Modifier.fillMaxWidth(),
                            text = {
                                Text(eachMovieGenre.name, style = MaterialTheme.typography.bodyLarge)
                            }, onClick = {
                                currentSelectedGenre = eachMovieGenre.name
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }

            // director
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Director")
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)) {
                TextField(
                    modifier = Modifier
                        .weight(50f),
                    placeholder = {
                        Text(text = "First Name")
                    },
                    value = movieDetails.director.firstName,
                    onValueChange = {

                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                TextField(
                    modifier = Modifier
                        .weight(50f),
                    placeholder = {
                        Text(text = "Last Name")
                    },
                    value = movieDetails.director.lastName,
                    onValueChange = {

                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )
                )
            }

            // actors
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Actors")
            LazyColumn(
                modifier = Modifier.heightIn(0.dp, 3000.dp),
                userScrollEnabled = false) {
                itemsIndexed(movieDetails.actors) { index, eachActor ->

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)) {
                        TextField(
                            modifier = Modifier
                                .weight(50f),
                            placeholder = {
                                Text(text = "First Name")
                            },
                            value = eachActor.firstName,
                            onValueChange = {

                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        TextField(
                            modifier = Modifier
                                .weight(50f),
                            placeholder = {
                                Text(text = "Last Name")
                            },
                            value = eachActor.lastName,
                            onValueChange = {

                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            )
                        )
                    }
                }
            }

            ListItem(modifier = Modifier.clickable {

            },
            headlineContent = {
                    Text(text = "Add Actor")
            },
            trailingContent = {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null)
            })

            // producers
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Producers")
            LazyColumn(
                modifier = Modifier.heightIn(0.dp, 3000.dp),
                userScrollEnabled = false) {
                itemsIndexed(movieDetails.producers) { index, eachProducer ->

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)) {
                        TextField(
                            modifier = Modifier
                                .weight(50f),
                            placeholder = {
                                Text(text = "First Name")
                            },
                            value = eachProducer.firstName,
                            onValueChange = {

                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        TextField(
                            modifier = Modifier
                                .weight(50f),
                            placeholder = {
                                Text(text = "Last Name")
                            },
                            value = eachProducer.lastName,
                            onValueChange = {

                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            )
                        )
                    }
                }
            }

            ListItem(modifier = Modifier.clickable {

            },
                headlineContent = {
                    Text(text = "Add Producer")
                },
                trailingContent = {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null)
                })
        }
    }

}

@Preview
@Composable
fun MovieDetailScreenPreview() {
    val previewMovieDetailViewState = MovieDetailViewState(
        title = "The Godfather",
        yearReleased = "1972",
        genre = "Crime",
        director = FullName(firstName = "Francis", lastName = "Ford Coppola"),
        actors = listOf(
            FullName(firstName = "Marlon", lastName = "Brando"),
            FullName(firstName = "Al", lastName = "Pacino")
        ),
        producers = listOf(
            FullName(firstName = "Albert", lastName = "Ruddy"),
            FullName(firstName = "Robert", lastName = "Evans")
        )
    )

    val viewModel = MovieDetailViewModel()

    viewModel.onReceive(Intent.InitialState(previewMovieDetailViewState))

    MovieDetailScreen(viewModel = viewModel,
        onDismissScreen = {})
}