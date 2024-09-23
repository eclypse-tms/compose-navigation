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
import com.example.compose.navigation.ui.actor.AddActorScreen
import com.example.compose.navigation.ui.detail.MovieDetailScreen
import com.example.compose.navigation.ui.detail.MovieDetailViewModel
import com.example.compose.navigation.ui.list.MovieListScreen
import com.example.compose.navigation.ui.list.MovieListViewModel
import com.example.compose.navigation.ui.producer.AddProducerScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: AppDestinations) {

    NavHost(modifier = modifier,
        navController = navController,
        startDestination = startDestination) {


        composable<AppDestinations.MovieList> { navBackStackEntry ->
            val movieListViewModel = hiltViewModel<MovieListViewModel>()

            MovieListScreen(viewModel = movieListViewModel,
                onMovieSelected = { currentlySelectedMovieId ->
                    navController.navigate(AppDestinations.MovieDetails(movieId = currentlySelectedMovieId))
                },
                onAddNewMovie = {
                    navController.navigate(AppDestinations.AddOrEditMovie())
                }
            )
        }

        navigation<AppDestinations.MovieDetails>(startDestination = AppDestinations.AddOrEditMovie()) {

            composable<AppDestinations.AddOrEditMovie> { navBackStackEntry ->
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

                val movieDetailViewModel = navBackStackEntry.sharedViewModel<MovieDetailViewModel>(navController)

                // region MovieDetail
                MovieDetailScreen(
                    viewModel = movieDetailViewModel,
                    onDismissScreen = { navController.popBackStack() },
                    onAddOrEditProducer = {
                        navController.navigate(AppDestinations.ProducerDetail)
                    },
                    onAddOrEditActor = {
                        navController.navigate(AppDestinations.ActorDetail)
                    }
                )
                // endregion
            }

            // region ActorDetail
            composable<AppDestinations.ActorDetail> { navBackStackEntry ->

                val movieDetailViewModel = navBackStackEntry.sharedViewModel<MovieDetailViewModel>(navController)

                AddActorScreen(
                    viewModel = movieDetailViewModel,
                    onDismissScreen = { navController.popBackStack() },
                )
            }
            // endregion

            // region ProducerDetail
            composable<AppDestinations.ProducerDetail> { navBackStackEntry ->

                val movieDetailViewModel = navBackStackEntry.sharedViewModel<MovieDetailViewModel>(navController)

                AddProducerScreen(
                    viewModel = movieDetailViewModel,
                    onDismissScreen = { navController.popBackStack() },
                )
            }
            // endregion
        }
    }
}


@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavHostController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry: NavBackStackEntry = remember(key1 = this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return hiltViewModel(parentEntry)
}