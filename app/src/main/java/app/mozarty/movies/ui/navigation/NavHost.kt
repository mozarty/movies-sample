package app.mozarty.movies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.mozarty.movies.details.ui.composable.MovieDetailsLayout
import app.mozarty.movies.discover.ui.composable.DiscoverLayout
import app.mozarty.movies.viewmodel.MoviesViewModel


@Composable
fun navHost(moviesViewModel: MoviesViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationDestinations.discoverDestination
    ) {
        composable(NavigationDestinations.discoverDestination) {
            DiscoverLayout(
                moviesViewModel,
                navController
            )
        }
        composable(NavigationDestinations.detailsDestination) {
            MovieDetailsLayout(
                moviesViewModel, navController
            )
        }
    }
}


object NavigationDestinations {
    const val discoverDestination = "discover"
    const val detailsDestination = "details"
}