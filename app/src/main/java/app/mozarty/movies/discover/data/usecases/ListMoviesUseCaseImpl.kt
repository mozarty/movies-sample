package app.mozarty.movies.discover.data.usecases

import app.mozarty.movies.data.error.MovieServiceException
import app.mozarty.movies.data.repository.MovieRepository
import app.mozarty.movies.discover.data.dto.MovieListResultsPage
import retrofit2.HttpException


class ListMoviesUseCaseImpl(private val movieRepository: MovieRepository) : ListMoviesUseCase {

    override suspend fun invoke(page: Int): Result<MovieListResultsPage> {
        try {
            val movieListResponse = movieRepository.listMovies(page)

            val serviceConfig = movieRepository.getServiceConfig()
            serviceConfig?.let {
                //update poster URL
                movieListResponse.results.map {
                    it.posterPath =
                        movieRepository.generateFullPosterPath(serviceConfig, it.posterPath)
                }
            }
            return Result.success(movieListResponse)

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