package com.example.compose.navigation.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.compose.navigation.ui.director.Director
import com.example.compose.navigation.ui.theme.NavyBlue
import com.example.compose.navigation.ui.theme.WayfinderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(viewModel: MovieDetailViewModel,
                      onDismissScreen: () -> Unit,
                      onAddNewActor: () -> Unit,
                      onAddNewProducer: () -> Unit) {

    val movieDetails: MovieDetailViewState by viewModel.movieDetailViewStateFlow.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var currentSelectedGenre by remember { mutableStateOf(movieDetails.genre) }


    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                    Text(text = "Movie Details")
                },
                    navigationIcon = {
                IconButton(onClick = onDismissScreen) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            })
    }) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .consumeWindowInsets(innerPadding)) {

            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
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
                                    Text(
                                        eachMovieGenre.name,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                },
                                onClick = {
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
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
                    userScrollEnabled = false
                ) {
                    itemsIndexed(movieDetails.actors) { _, eachActor ->
                        ListItem(headlineContent = {
                            Text(text = eachActor.displayName())
                        })
                        HorizontalDivider()
                    }
                }

                ListItem(modifier = Modifier.clickable {
                    onAddNewActor()
                },
                    headlineContent = {
                        Row {
                            Icon(Icons.Filled.Add, null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Add Actor")
                        }

                    },
                    trailingContent = {
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null)
                    })

                // producers
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Producers")
                LazyColumn(
                    modifier = Modifier.heightIn(0.dp, 3000.dp),
                    userScrollEnabled = false
                ) {
                    itemsIndexed(movieDetails.producers) { _, eachProducer ->

                        ListItem(headlineContent = {
                            Text(text = eachProducer.displayName())
                        })
                        HorizontalDivider()
                    }
                }

                ListItem(modifier = Modifier.clickable {
                    onAddNewProducer()
                },
                    headlineContent = {
                        Row {
                            Icon(Icons.Filled.Add, null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Add Producer")
                        }
                    },
                    trailingContent = {
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null)
                    })
            }

            Spacer(modifier = Modifier.weight(1f))


            Row(modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically) {

                Button(modifier = Modifier.widthIn(min = 150.dp),
                    colors = ButtonColors(
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

                Button(modifier = Modifier.widthIn(min = 150.dp),
                    onClick = {

                    }) {
                    Text(text = "Save")
                }
            }
        }
    }

}

@Preview(heightDp = 800)
@Composable
fun MovieDetailScreenPreview() {
    val previewMovieDetailViewState = MovieDetailViewState(
        title = "The Godfather",
        yearReleased = "1972",
        genre = "Crime",
        director = Director(firstName = "Francis", lastName = "Ford Coppola"),
        actors = listOf(
            //Actor(firstName = "Marlon", lastName = "Brando", dob = 1954),
            //Actor(firstName = "Al", lastName = "Pacino", dob = 1948)
        ),
        producers = listOf(
            //Producer(firstName = "Albert", lastName = "Ruddy"),
            //Producer(firstName = "Robert", lastName = "Evans"),
            //Producer(firstName = "Bill", lastName = "Norman"),
        )
    )

    val viewModel = MovieDetailViewModel()

    viewModel.onReceive(Intent.InitialState(previewMovieDetailViewState))

    WayfinderTheme {
        MovieDetailScreen(viewModel = viewModel,
            onDismissScreen = {},
            onAddNewProducer = {},
            onAddNewActor = {})
    }
}