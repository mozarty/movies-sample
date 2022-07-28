package app.mozarty.movies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import app.mozarty.movies.details.viewmodel.MovieDetailsViewModel
import app.mozarty.movies.discover.viewmodel.DiscoverViewModel
import app.mozarty.movies.ui.navigation.navHost
import app.mozarty.movies.ui.theme.MoviesSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : ComponentActivity() {


    private val discoverViewModel: DiscoverViewModel by viewModels()
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesSampleTheme {
                Surface(
                    Modifier
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surface,
                ) {
                    navHost(discoverViewModel, movieDetailsViewModel)
                }
            }
        }
    }
}