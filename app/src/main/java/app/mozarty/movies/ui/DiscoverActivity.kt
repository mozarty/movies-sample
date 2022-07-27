package app.mozarty.movies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.mozarty.movies.ui.composable.DiscoverLayout
import app.mozarty.movies.ui.theme.MoviesSampleTheme
import app.mozarty.movies.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverActivity : ComponentActivity() {


    private val movieListViewModel: MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesSampleTheme {
                DiscoverLayout(movieListViewModel = movieListViewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesSampleTheme {
        Greeting("Android")
    }
}