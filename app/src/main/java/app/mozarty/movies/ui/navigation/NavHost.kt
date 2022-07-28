package app.mozarty.movies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.mozarty.movies.details.ui.MovieDetailsLayout
import app.mozarty.movies.details.viewmodel.MovieDetailsViewModel
import app.mozarty.movies.discover.ui.DiscoverLayout
import app.mozarty.movies.discover.viewmodel.DiscoverViewModel


@Composable
fun navHost(discoverViewModel: DiscoverViewModel, movieDetailsViewModel: MovieDetailsViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationDestinations.discoverDestination
    ) {
        composable(NavigationDestinations.discoverDestination) {
            DiscoverLayout(
                discoverViewModel,
                navController
            )
        }
        composable(
            NavigationDestinations.detailsDestination,
            arguments = listOf(navArgument(NavigationArguments.movieID) {
                type = NavType.StringType
            })
        ) {
            MovieDetailsLayout(
                movieDetailsViewModel,
                it.arguments?.getString(NavigationArguments.movieID).orEmpty()
            )
        }
    }
}
