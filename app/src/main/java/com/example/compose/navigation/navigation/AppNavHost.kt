package com.example.compose.navigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.navigation.ui.detail.MovieDetailScreen
import com.example.compose.navigation.ui.detail.MovieDetailViewModel
import com.example.compose.navigation.ui.list.MovieListScreen
import com.example.compose.navigation.ui.list.MovieListViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: AppDestinations) {

    NavHost(navController = navController, startDestination = startDestination) {


        composable<AppDestinations.MovieList> { navBackStackEntry ->
            val movieListViewModel = hiltViewModel<MovieListViewModel>()

            MovieListScreen(viewModel = movieListViewModel,
                onMovieSelected = { currentlySelectedMovieId ->
                    navController.navigate(AppDestinations.MovieDetail(movieId = currentlySelectedMovieId))
                },
                onAddNewMovie = {
                    navController.navigate(AppDestinations.MovieDetail())
                }
            )
        }

        // region AddNewOrEditNote
        composable<AppDestinations.MovieDetail> { navBackStackEntry ->
            val movieDetailViewModel = hiltViewModel<MovieDetailViewModel>()

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

            MovieDetailScreen(
                viewModel = movieDetailViewModel,
                onDismissScreen = { navController.popBackStack() },
            )
        }
    }
}