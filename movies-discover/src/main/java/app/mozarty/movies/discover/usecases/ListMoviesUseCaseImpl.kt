package app.mozarty.movies.discover.usecases

import app.mozarty.movies.data.dto.MovieListResultsPage
import app.mozarty.movies.data.error.MovieException
import app.mozarty.movies.data.repository.MovieRepository
import app.mozarty.movies.usecases.ListMoviesUseCase


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

        } catch (e: MovieException) {
            return Result.failure(e)
        }
    }

}