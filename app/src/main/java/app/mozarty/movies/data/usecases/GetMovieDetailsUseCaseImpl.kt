package app.mozarty.movies.data.usecases

import app.mozarty.movies.data.dto.MovieDetails
import app.mozarty.movies.data.error.MovieServiceException
import app.mozarty.movies.data.repository.MovieRepository
import retrofit2.HttpException

class GetMovieDetailsUseCaseImpl(private val movieRepository: MovieRepository) :
    GetMovieDetailsUseCase {

    override suspend fun invoke(movieID: String): Result<MovieDetails> {
        try {
            val movieDetailsResponse = movieRepository.getMovieDetails(movieID)
            return Result.success(movieDetailsResponse)

        } catch (e: Throwable) {
            if (e is HttpException) {
                return when (e.code()) {
                    // create exception from the error code
                    401, 404 ->
                        Result.failure(MovieServiceException(e.message(), e.code()))
                    // create exception from network error
                    else -> Result.failure(e)
                }
            }
            // create exception from general error
            return Result.failure(e)
        }
    }
}