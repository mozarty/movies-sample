package app.mozarty.movies.data.usecases

import app.mozarty.movies.data.dto.MovieDetails
import app.mozarty.movies.data.dto.MovieListResultsPage
import arrow.core.Either

interface GetMovieDetailsUseCase {
    suspend operator fun invoke(movieID: String): Either<Throwable, MovieDetails>
}