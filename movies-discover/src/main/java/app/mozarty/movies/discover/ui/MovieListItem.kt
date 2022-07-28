package app.mozarty.movies.discover.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import app.mozarty.movies.data.dto.MovieOutline
import app.mozarty.movies.ui.SampleMovieOutlineProvider
import app.mozarty.movies.ui.TestTags
import app.mozarty.movies.ui.theme.MoviesSampleTheme
import app.mozarty.movies_sample_core.R
import com.skydoves.landscapist.coil.CoilImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListItem(movie: MovieOutline, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .semantics { testTag = TestTags.MovieListItem },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
        ) {
            CoilImage(
                imageModel = movie.posterPath,
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.FillWidth,
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
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .align(CenterVertically),

                )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp, end = 8.dp),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = movie.title,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {

                    Image(
                        painter = painterResource(R.drawable.ic_baseline_star_24),
                        modifier = Modifier
                            .align(CenterVertically)
                            .padding(end = 2.dp),
                        contentDescription = ""
                    )
                    Text(
                        text = movie.score,
                        style = MaterialTheme.typography.titleSmall,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = movie.releaseDate,
                        style = MaterialTheme.typography.titleSmall,
                    )

                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                    text = movie.overview,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MovieListItemPreviewDark(
    @PreviewParameter(
        SampleMovieOutlineProvider::class,
        1
    ) movie: MovieOutline
) {
    MoviesSampleTheme(true) {
        MovieListItem(movie) { }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListItemPreview(
    @PreviewParameter(
        SampleMovieOutlineProvider::class,
        1
    ) movie: MovieOutline
) {
    MoviesSampleTheme(false) {
        MovieListItem(movie) { }
    }
}