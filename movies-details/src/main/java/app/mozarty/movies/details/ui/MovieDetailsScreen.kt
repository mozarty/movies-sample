package app.mozarty.movies.details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import app.mozarty.movies.data.dto.MovieDetails
import app.mozarty.movies.ui.SampleMovieDetailsProvider
import app.mozarty.movies.ui.theme.MoviesSampleTheme
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import app.mozarty.movies_sample_core.R

@Composable
fun MovieDetailsScreen(movie: MovieDetails) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                CoilImage(
                    imageModel = movie.posterPath,
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.FillBounds,
                    circularReveal = CircularReveal(500),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.4f),
                    // shows a placeholder while loading the image.
                    previewPlaceholder = R.drawable.ic_movie_poster,
                    // shows an error ImageBitmap when the request failed
                    failure = {
                        Image(
                            painter = painterResource(R.drawable.ic_movie_poster),
                            contentDescription = ""
                        )
                    },
                    loading = {
                        Image(
                            painter = painterResource(R.drawable.ic_movie_poster),
                            contentDescription = ""
                        )
                    },

                    )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .align(Alignment.Start)
                ) {


                    Image(
                        painter = painterResource(R.drawable.ic_baseline_star_24),
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                            .align(Alignment.CenterVertically)
                            .padding(end = 2.dp),
                        contentDescription = ""
                    )
                    Text(
                        text = stringResource(
                            id = R.string.movie_rating_format,
                            movie.score,
                            movie.voteCount
                        ),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier
                        .align(Alignment.Start),
                    text = stringResource(
                        id = R.string.movie_release_format,
                        movie.releaseDate
                    ),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )


                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = movie.overview,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

            }
        }
}

@Preview(showBackground = false)
@Composable
fun MovieDetailsPreviewDark(
    @PreviewParameter(
        SampleMovieDetailsProvider::class,
        1
    ) movie: MovieDetails
) {
    MoviesSampleTheme(true) {
        MovieDetailsScreen(movie)
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview(
    @PreviewParameter(
        SampleMovieDetailsProvider::class,
        1
    ) movie: MovieDetails
) {
    MoviesSampleTheme(false) {
        MovieDetailsScreen(movie)
    }
}