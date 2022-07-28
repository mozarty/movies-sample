package app.mozarty.movies.details.usecases

import app.mozarty.movies.data.dto.MovieDetails
import app.mozarty.movies.data.error.MovieException
import app.mozarty.movies.data.repository.MovieRepository
import app.mozarty.movies.usecases.GetMovieDetailsUseCase

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

        } catch (e: MovieException) {
            return Result.failure(e)
        }
    }
}