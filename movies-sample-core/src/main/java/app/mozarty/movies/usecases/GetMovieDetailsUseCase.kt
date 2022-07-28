package app.mozarty.movies.usecases

import app.mozarty.movies.data.dto.MovieDetails

interface GetMovieDetailsUseCase {
    suspend operator fun invoke(movieID: String): Result<MovieDetails>
}