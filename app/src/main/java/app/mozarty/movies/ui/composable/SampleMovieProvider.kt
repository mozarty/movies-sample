package app.mozarty.movies.ui.composable

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.mozarty.movies.data.dto.MovieDetails
import app.mozarty.movies.data.dto.MovieListResultsPage
import app.mozarty.movies.data.dto.MovieOutline
import app.mozarty.movies.data.dto.ServiceConfig
import app.mozarty.movies.data.repository.MovieRepository
import app.mozarty.movies.data.service.ConfigService
import app.mozarty.movies.data.service.MovieService
import app.mozarty.movies.data.usecases.ListMoviesUseCaseImpl
import app.mozarty.movies.viewmodel.MovieListViewModel


class SampleViewModelProvider : PreviewParameterProvider<MovieListViewModel> {
    override val values: Sequence<MovieListViewModel>
        get() = sequenceOf(
            MovieListViewModel(
                ListMoviesUseCaseImpl(
                    MovieRepository(
                        SampleMovieService(),
                        SampleConfigService()
                    )
                )
            )
        )
}


class SampleMovieService : MovieService {
    override suspend fun listMovies(
        page: Int,
        apiKey: String,
        language: String
    ): MovieListResultsPage {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieDetails(
        movieID: String,
        apiKey: String,
        language: String
    ): MovieDetails {
        TODO("Not yet implemented")
    }
}

class SampleConfigService : ConfigService {
    override suspend fun getConfiguration(apiKey: String): ServiceConfig {
        TODO("Not yet implemented")
    }
}


class SampleMovieOutlineProvider : PreviewParameterProvider<MovieOutline> {
    override val values: Sequence<MovieOutline>
        get() = sequenceOf(
            MovieOutline(
                adult = false,
                backdropPath = "",
                genreIDs = emptyList(),
                id = "1",
                language = "ar",
                originalTitle = "Scott Pilgrim Vs. The World",
                overview = "Scott Pilgrim meets Ramona and instantly falls in love with her. But when he meets one of her exes at a band competition, he realises that he has to deal with all seven of her exes to woo her.",
                popularity = "10000",
                posterPath = "",
                releaseDate = "October 21, 2010",
                score = "10",
                title = "Scott Pilgrim Vs. The World",
                video = false,
                voteCount = "10000"
            ),
        )

}