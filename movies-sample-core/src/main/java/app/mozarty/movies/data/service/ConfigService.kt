package app.mozarty.movies.data.service

import app.mozarty.movies.data.dto.ServiceConfig
import app.mozarty.movies.data.service.MovieService.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface ConfigService {


    @GET("configuration")
    suspend fun getConfiguration(
        @Query("api_key") apiKey: String = API_KEY,
    ): ServiceConfig


}