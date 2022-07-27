package app.mozarty.movies.discover.data.usecases

import app.mozarty.movies.discover.data.dto.MovieListResultsPage

interface ListMoviesUseCase {
    suspend operator fun invoke(page: Int): Result<MovieListResultsPage>
}