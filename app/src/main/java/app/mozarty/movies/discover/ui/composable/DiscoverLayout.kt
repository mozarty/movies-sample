package app.mozarty.movies.discover.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import app.mozarty.movies.viewmodel.MoviesViewModel
import app.mozarty.movies.viewmodel.MoviesViewModel.ViewState.*

@Composable
fun DiscoverLayout(moviesViewModel: MoviesViewModel, navController: NavHostController) {

    val viewState by moviesViewModel.getDiscoverViewState().observeAsState(initial = Loading)

    when (viewState) {
        Loading -> {}


        Success -> {
            MovieList(moviesViewModel,navController)
        }
        Error -> {}
    }
}