package app.mozarty.movies.data.usecases

import app.mozarty.movies.data.dto.MovieListResultsPage
import app.mozarty.movies.data.error.MovieServiceException
import app.mozarty.movies.data.repository.MovieRepository
import arrow.core.Either
import retrofit2.HttpException


class ListMoviesUseCaseImpl(private val movieRepository: MovieRepository) : ListMoviesUseCase {

    override suspend fun invoke(page: Int): Either<Throwable, MovieListResultsPage> {
        try {
            val movieListResponse = movieRepository.listMovies(page)
            return Either.Right(movieListResponse)

        } catch (e: Throwable) {
            if (e is HttpException) {
                return when (e.code()) {
                    // create exception from the error code
                    401, 404 ->
                        Either.Left(MovieServiceException(e.message(), e.code()))
                    // create exception from network error
                    else -> Either.Left(e)
                }
            }
            // create exception from general error
            return Either.Left(e)
        }
    }
}