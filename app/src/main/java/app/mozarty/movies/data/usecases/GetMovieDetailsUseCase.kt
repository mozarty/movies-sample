package app.mozarty.movies.data.usecases

import app.mozarty.movies.data.dto.MovieDetails
import app.mozarty.movies.data.dto.MovieListResultsPage

interface GetMovieDetailsUseCase {
    suspend operator fun invoke(movieID: String): Result<MovieDetails>
}