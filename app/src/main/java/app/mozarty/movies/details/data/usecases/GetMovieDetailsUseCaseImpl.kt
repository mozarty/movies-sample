package app.mozarty.movies.details.data.usecases

import app.mozarty.movies.data.error.MovieServiceException
import app.mozarty.movies.data.repository.MovieRepository
import app.mozarty.movies.details.data.dto.MovieDetails
import retrofit2.HttpException

class GetMovieDetailsUseCaseImpl(private val movieRepository: MovieRepository) :
    GetMovieDetailsUseCase {

    override suspend fun invoke(movieID: String): Result<MovieDetails> {
        try {
            val serviceConfig = movieRepository.getServiceConfig()
            val movieDetailsResponse = movieRepository.getMovieDetails(movieID)
            serviceConfig?.let {
                //update poster URL
            movieDetailsResponse.posterPath =
                movieRepository.generateFullPosterPath(
                    serviceConfig,
                    movieDetailsResponse.posterPath
                )
            }
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