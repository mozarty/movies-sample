package app.mozarty.movies.discover.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.mozarty.movies.ui.composable.TestTags
import app.mozarty.movies.ui.navigation.NavigationDestinations
import app.mozarty.movies.viewmodel.MoviesViewModel


@Composable
fun MovieList(movieViewModel: MoviesViewModel, navController: NavHostController) {

    val moviesList by movieViewModel.getMovieList().observeAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier.testTag(TestTags.MovieList),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(items = moviesList, { movie -> movie.id }) {
            MovieListItem(movie = it, onClick = {
                movieViewModel.getMovieDetails(it.id)
                navController.navigate(NavigationDestinations.detailsDestination)
            })
        }
    }
}