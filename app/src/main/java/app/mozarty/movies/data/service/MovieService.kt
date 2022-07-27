package app.mozarty.movies.data.service

import app.mozarty.movies.data.dto.MovieDetails
import app.mozarty.movies.data.dto.MovieListResultsPage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    suspend fun listMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en_US"
    ): MovieListResultsPage


    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieID: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en_US"
    ): MovieDetails


    companion object {
        const val API_KEY = "c9856d0cb57c3f14bf75bdc6c063b8f3"
    }
}