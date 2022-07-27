package app.mozarty.movies.ui.composable

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.mozarty.movies.data.dto.ImageConfig
import app.mozarty.movies.data.dto.ServiceConfig
import app.mozarty.movies.data.repository.MovieRepository
import app.mozarty.movies.data.service.ConfigService
import app.mozarty.movies.data.service.MovieService
import app.mozarty.movies.details.data.dto.MovieDetails
import app.mozarty.movies.details.data.usecases.GetMovieDetailsUseCaseImpl
import app.mozarty.movies.discover.data.dto.MovieListResultsPage
import app.mozarty.movies.discover.data.dto.MovieOutline
import app.mozarty.movies.discover.data.usecases.ListMoviesUseCaseImpl
import app.mozarty.movies.viewmodel.MoviesViewModel


class SampleMovieViewModelProvider : PreviewParameterProvider<MoviesViewModel> {
    override val values: Sequence<MoviesViewModel>
        get() {
            val movieRepository = MovieRepository(
                SampleMovieService(),
                SampleConfigService()
            )
            return sequenceOf(
                MoviesViewModel(
                    ListMoviesUseCaseImpl(
                        movieRepository
                    ),
                    GetMovieDetailsUseCaseImpl(
                        movieRepository
                    )
                )
            )
        }
}

class SampleMovieService : MovieService {
    override suspend fun listMovies(
        page: Int,
        apiKey: String,
        language: String
    ): MovieListResultsPage {
        return MovieListResultsPage(
            results = List(5) { sampleMovieOutline() },
            page = 1,
            totalPages = 10,
            totalResults = 100
        )
    }

    override suspend fun getMovieDetails(
        movieID: String,
        apiKey: String,
        language: String
    ): MovieDetails {
        return sampleMovieDetails()
    }


}

class SampleConfigService : ConfigService {
    override suspend fun getConfiguration(apiKey: String): ServiceConfig {
        return ServiceConfig(
            ImageConfig(
                baseURL = "",
                secureBaseURL = "",
                posterSizes = emptyList()
            )
        )
    }
}


class SampleMovieOutlineProvider : PreviewParameterProvider<MovieOutline> {
    override val values: Sequence<MovieOutline>
        get() = sequenceOf(
            sampleMovieOutline(),
        )


}

class SampleMovieDetailsProvider : PreviewParameterProvider<MovieDetails> {
    override val values: Sequence<MovieDetails>
        get() = sequenceOf(
            sampleMovieDetails(),
        )


}

private fun sampleMovieDetails() = MovieDetails(
    id = "",
    voteCount = "1000",
    video = false,
    title = "Wonder",
    score = "10.1",
    releaseDate = "December 1, 2017",
    posterPath = "",
    popularity = "100000",
    overview = "WONDER tells the incredibly inspiring and heartwarming story of August Pullman. Born with facial differences that, up until now, have prevented him from going to a mainstream school, Auggie becomes the most unlikely of heroes when he enters the local fifth grade. As his family, his new classmates, and the larger community all struggle to discover their compassion and acceptance, Auggie's extraordinary journey will unite them all and prove you can't blend in when you were born to stand out.",
    originalTitle = "Wonder",
    language = "en",
    backdropPath = "",
    adult = false,
    budget = 0,
    genres = emptyList(),
    revenue = 1,
    runtime = 100,
    status = "Released",
    tagline = "Choose kind"
)

private fun sampleMovieOutline() = MovieOutline(
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
)