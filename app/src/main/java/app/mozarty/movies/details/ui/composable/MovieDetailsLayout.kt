package app.mozarty.movies.details.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import app.mozarty.movies.viewmodel.MoviesViewModel

@Composable
fun MovieDetailsLayout(moviesViewModel: MoviesViewModel, navController: NavHostController) {

    val viewState by moviesViewModel.getDetailsViewState()
        .observeAsState(initial = MoviesViewModel.ViewState.Loading)

    when (viewState) {
        MoviesViewModel.ViewState.Loading -> {}


        MoviesViewModel.ViewState.Success -> {
            val movie by moviesViewModel.getMovieDetails()
                .observeAsState()

            movie?.let { movie ->
                MovieDetailsScreen(movie)
            }
        }
        MoviesViewModel.ViewState.Error -> {}
    }


}




