package app.mozarty.movies.details.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import app.mozarty.movies.details.viewmodel.MovieDetailsViewModel
import app.mozarty.movies.details.viewmodel.MovieDetailsViewModel.ViewState.*
import app.mozarty.movies.ui.LoadingState

@Composable
fun MovieDetailsLayout(
    movieDetailsViewModel: MovieDetailsViewModel,
    movieID: String
) {

    val viewState by movieDetailsViewModel.getDetailsViewState()
        .observeAsState(initial = Loading)

    movieDetailsViewModel.currentMovieUpdated(movieID)

    when (viewState) {
        Loading -> {
            LoadingState()
        }

        Success -> {
            val movie by movieDetailsViewModel.getMovieDetails()
                .observeAsState()

            movie?.let { movie ->
                MovieDetailsScreen(movie)
            }
        }
        Error -> {
            MovieDetailsErrorState()
        }
    }


}




