package app.mozarty.movies.data.repository

import app.mozarty.movies.data.dto.MovieDetails
import app.mozarty.movies.data.dto.MovieListResultsPage
import app.mozarty.movies.data.service.MovieService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun listMovies(page: Int): MovieListResultsPage {
        return movieService.listMovies(page)
    }


    suspend fun getMovieDetails(movieID: String): MovieDetails {
        return movieService.getMovieDetails(movieID)
    }
}