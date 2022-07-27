package app.mozarty.movies.data.usecases

import app.mozarty.movies.data.dto.MovieListResultsPage

interface ListMoviesUseCase {
    suspend operator fun invoke(page: Int): Result<MovieListResultsPage>
}