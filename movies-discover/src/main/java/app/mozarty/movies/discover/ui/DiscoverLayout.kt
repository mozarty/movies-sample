package app.mozarty.movies.discover.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import app.mozarty.movies.discover.viewmodel.DiscoverViewModel
import app.mozarty.movies.discover.viewmodel.DiscoverViewModel.ViewState.*
import app.mozarty.movies.ui.LoadingState

@Composable
fun DiscoverLayout(discoverViewModel: DiscoverViewModel, navController: NavHostController) {

    val viewState by discoverViewModel.getDiscoverViewState().observeAsState(initial = Loading)

    when (viewState) {
        Loading -> {
            LoadingState()
        }
        Success -> {
            MovieList(discoverViewModel, navController)
        }
        Error -> {
            MovieListErrorState()
        }
    }
}