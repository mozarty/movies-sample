package app.mozarty.movies.discover.ui

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
import app.mozarty.movies.discover.viewmodel.DiscoverViewModel
import app.mozarty.movies.ui.TestTags
import app.mozarty.movies.ui.navigation.NavigationDestinations


@Composable
fun MovieList(discoverViewModel: DiscoverViewModel, navController: NavHostController) {

    val moviesList by discoverViewModel.getMovieList().observeAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier.testTag(TestTags.MovieList),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(items = moviesList, { movie -> movie.id }) {
            MovieListItem(movie = it, onClick = {
                navController.navigate(NavigationDestinations.detailsDestinationTemplate + it.id)
            })
        }
    }
}