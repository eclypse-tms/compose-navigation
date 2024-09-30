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
import com.example.compose.navigation.ui.actor.AddActorScreen
import com.example.compose.navigation.ui.detail.MovieDetailScreen
import com.example.compose.navigation.ui.detail.MovieDetailViewModel
import com.example.compose.navigation.ui.list.MovieListScreen
import com.example.compose.navigation.ui.list.MovieListViewModel
import com.example.compose.navigation.ui.producer.AddProducerScreen
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
                onMovieSelected = { currentlySelectedMovieId ->
                    navController.navigate(route = AppDestination.EditMovie(movieId = currentlySelectedMovieId))
                },
                onAddNewMovie = {
                    navController.navigate(route = AppDestination.EditMovie(movieId = null))
                }
            )
        }

        navigation<AppDestination.MovieDetails>(startDestination = AppDestination.EditMovie()) {

            composable<AppDestination.EditMovie> { navBackStackEntry ->
                /*
                val currentBackStackEntry = navController.currentBackStackEntry
                if (currentBackStackEntry != null) {
                    val selectedGrowStageId = currentBackStackEntry.savedStateHandle.get<GrowthStage>(GrowthStageViewModel.SELECTED_GROWTH_STAGE_KEY)
                    if (selectedGrowStageId != null) {
                        addNoteViewModel.selectedGrowthStageFlow.value = selectedGrowStageId
                    }

                    val selectedIssueType = currentBackStackEntry.savedStateHandle.get<IssueTypeAndConcernLevel>(IssueTypeViewModel.SELECTED_ISSUE_TYPE_KEY)
                    if (selectedIssueType != null) {
                        addNoteViewModel.selectedIssueTypeFlow.value = selectedIssueType
                    }
                }
                */
                val movieDetailViewModel = navBackStackEntry.scopedViewModel<MovieDetailViewModel>(navController)


                val newlyCreatedProducer = navBackStackEntry.savedStateHandle.get<Producer>("producer")
                if (newlyCreatedProducer != null) {
                    movieDetailViewModel.producersFlow.tryEmit(newlyCreatedProducer)
                }

                // region MovieDetail
                MovieDetailScreen(
                    viewModel = movieDetailViewModel,
                    onDismissScreen = { navController.popBackStack() },
                    onAddOrEditProducer = {
                        navController.navigate(route = AppDestination.ProduceDetails(producer = it))
                    },
                    onAddOrEditActor = {
                        navController.navigate(AppDestination.ActorDetails(actor = null))
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

                val movieDetailViewModel = navBackStackEntry.scopedViewModel<MovieDetailViewModel>(navController)

                AddActorScreen(
                    viewModel = movieDetailViewModel,
                    onDismissScreen = { navController.popBackStack() },
                )
            }
            // endregion
        }

        // region ProducerDetail
        composable<AppDestination.ProduceDetails>(
            typeMap = mapOf(
                typeOf<Producer>() to parcelableType<Producer>(),
                typeOf<Producer?>() to parcelableType<Producer?>()
            )
        ) { navBackStackEntry ->

            val producerDetailViewModel = hiltViewModel<ProducerDetailViewModel>()

            AddProducerScreen(
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
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry: NavBackStackEntry = remember(key1 = this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return hiltViewModel(parentEntry)
}