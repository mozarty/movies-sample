package app.mozarty.movies.data.repository

import app.mozarty.movies.data.dto.MovieDetails
import app.mozarty.movies.data.dto.MovieListResultsPage
import app.mozarty.movies.data.dto.ServiceConfig
import app.mozarty.movies.data.service.ConfigService
import app.mozarty.movies.data.service.MovieService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val configService: ConfigService
) {

    private var serviceConfig: ServiceConfig? = null

    fun getServiceConfig(): ServiceConfig? {
        return serviceConfig
    }

    //results are cached so network calls are not made unnecessarily
    private suspend fun updateConfigIfNeeded() {
        serviceConfig = configService.getConfiguration()
    }

    suspend fun listMovies(page: Int): MovieListResultsPage {
        updateConfigIfNeeded()
        return movieService.listMovies(page)
    }


    suspend fun getMovieDetails(movieID: String): MovieDetails {
        updateConfigIfNeeded()
        return movieService.getMovieDetails(movieID)
    }
}