package com.example.compose.navigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.compose.navigation.ui.actor.Actor
import com.example.compose.navigation.ui.actor.ActorDetailsScreen
import com.example.compose.navigation.ui.detail.MovieDetailsScreen
import com.example.compose.navigation.ui.detail.MovieDetailsViewModel
import com.example.compose.navigation.ui.list.MovieListScreen
import com.example.compose.navigation.ui.list.MovieListViewModel
import com.example.compose.navigation.ui.producer.ProducerDetailsScreen
import com.example.compose.navigation.ui.producer.Producer
import com.example.compose.navigation.ui.producer.ProducerDetailViewModel
import kotlin.reflect.typeOf

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: AppDestination) {

    NavHost(modifier = modifier,
        navController = navController,
        startDestination = startDestination) {

        composable<AppDestination.MovieList> { navBackStackEntry ->
            val movieListViewModel = hiltViewModel<MovieListViewModel>()

            MovieListScreen(viewModel = movieListViewModel,
                onMovieSelected = { movieId ->
                    navController.navigate(route = AppDestination.MovieDetails(movieId = movieId))
                },
                onAddNewMovie = {
                    navController.navigate(route = AppDestination.MovieDetails(movieId = null))
                }
            )
        }

        navigation<AppDestination.MovieDetailsParent>(startDestination = AppDestination.MovieDetails()) {

            composable<AppDestination.MovieDetails> { navBackStackEntry ->

                val movieDetailsViewModel = navBackStackEntry.scopedViewModel<MovieDetailsViewModel>(navController)


                val newlyCreatedProducer = navBackStackEntry.savedStateHandle.get<Producer>("producer")
                if (newlyCreatedProducer != null) {
                    movieDetailsViewModel.producersFlow.tryEmit(newlyCreatedProducer)
                }

                // region MovieDetail
                MovieDetailsScreen(
                    viewModel = movieDetailsViewModel,
                    onDismissScreen = { navController.popBackStack() },
                    onAddOrEditProducer = {
                        navController.navigate(route = AppDestination.ProducerDetails(producer = it))
                    },
                    onAddOrEditActor = {
                        navController.navigate(AppDestination.ActorDetails)
                    }
                )
                // endregion
            }

            // region ActorDetail
            composable<AppDestination.ActorDetails>(
                typeMap = mapOf(
                    typeOf<Actor>() to parcelableType<Actor>(),
                    typeOf<Actor?>() to parcelableType<Actor?>(),
                )
            ) { navBackStackEntry ->

                val movieDetailsViewModel = navBackStackEntry.scopedViewModel<MovieDetailsViewModel>(navController)

                ActorDetailsScreen(
                    viewModel = movieDetailsViewModel,
                    onDismissScreen = { navController.popBackStack() },
                )
            }
            // endregion
        }

        // region ProducerDetail
        composable<AppDestination.ProducerDetails>(
            typeMap = mapOf(
                typeOf<Producer>() to parcelableType<Producer>(),
                typeOf<Producer?>() to parcelableType<Producer?>()
            )
        ) { navBackStackEntry ->

            val producerDetailViewModel = hiltViewModel<ProducerDetailViewModel>()

            ProducerDetailsScreen(
                viewModel = producerDetailViewModel,
                onSaveProducer = { producer ->
                    val previousBackStackEntry = navController.previousBackStackEntry
                    if (previousBackStackEntry != null) {
                        previousBackStackEntry.savedStateHandle["producer"] = producer
                    }
                    navController.popBackStack()
                },
                onDismissScreen = { navController.popBackStack() },
            )
        }
        // endregion
    }
}


@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.scopedViewModel(navController: NavHostController): T {
    // if the destination route doesn't have a parent create a brand new instance
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry: NavBackStackEntry = remember(key1 = this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return hiltViewModel(parentEntry)
}