package app.mozarty.movies.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import app.mozarty.movies.viewmodel.MovieListViewModel
import app.mozarty.movies.viewmodel.MovieListViewModel.ViewState.*

@Composable
fun DiscoverLayout(movieListViewModel: MovieListViewModel) {

    val viewState by movieListViewModel.getViewState().observeAsState(initial = Loading)

    when (viewState) {
        Loading -> {}


        Success -> {
            MovieList(movieListViewModel)
        }
        Error -> {}
    }
}