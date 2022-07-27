package app.mozarty.movies.data.usecases

import app.mozarty.movies.data.dto.MovieListResultsPage
import arrow.core.Either

interface ListMoviesUseCase {
    suspend operator fun invoke(page: Int): Either<Throwable, MovieListResultsPage>
}