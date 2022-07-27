package app.mozarty.movies.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import app.mozarty.movies.viewmodel.MovieListViewModel


@Composable
fun MovieList(movieListViewModel: MovieListViewModel) {

    val moviesList by movieListViewModel.getMovieList().observeAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier.testTag(TestTags.MovieList),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(items = moviesList, { movie -> movie.id }) {
            MovieListItem(movie = it)
        }
    }
}