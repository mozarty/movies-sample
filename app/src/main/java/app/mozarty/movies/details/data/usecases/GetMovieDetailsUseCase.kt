package app.mozarty.movies.details.data.usecases

import app.mozarty.movies.details.data.dto.MovieDetails

interface GetMovieDetailsUseCase {
    suspend operator fun invoke(movieID: String): Result<MovieDetails>
}