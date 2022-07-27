package app.mozarty.movies.data.usecases

import app.mozarty.movies.data.dto.MovieDetails
import app.mozarty.movies.data.error.MovieServiceException
import app.mozarty.movies.data.repository.MovieRepository
import arrow.core.Either
import retrofit2.HttpException

class GetMovieDetailsUseCaseImpl(private val movieRepository: MovieRepository) :
    GetMovieDetailsUseCase {

    override suspend fun invoke(movieID: String): Either<Throwable, MovieDetails> {
        try {
            val movieDetailsResponse = movieRepository.getMovieDetails(movieID)
            return Either.Right(movieDetailsResponse)

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