package app.mozarty.movies.data.repository

import app.mozarty.movies.data.cache.CacheManager
import app.mozarty.movies.data.dto.MovieDetails
import app.mozarty.movies.data.dto.MovieListResultsPage
import app.mozarty.movies.data.dto.ServiceConfig
import app.mozarty.movies.data.error.ErrorType
import app.mozarty.movies.data.error.MovieException
import app.mozarty.movies.data.service.ConfigService
import app.mozarty.movies.data.service.MovieService
import com.google.gson.Gson
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val configService: ConfigService,
    private val cacheManager: CacheManager
) {

    private var serviceConfig: ServiceConfig? = null

    fun getServiceConfig(): ServiceConfig? {
        return serviceConfig
    }

    //results are cached so network calls are not made unnecessarily
    private suspend fun updateConfigIfNeeded() {
        cacheManager.get(SERVICE_CONFIG_CACHE).onSuccess {
            serviceConfig = Gson().fromJson(it,ServiceConfig::class.java)
        }.onFailure {
            serviceConfig = configService.getConfiguration()
            cacheManager.store(
                Gson().toJson(serviceConfig),
                SERVICE_CONFIG_CACHE,
                SERVICE_CONFIG_CACHE_AGE_DAYS
            )
        }
    }

    suspend fun listMovies(page: Int): MovieListResultsPage {
        try {
            updateConfigIfNeeded()
            return movieService.listMovies(page)
        } catch (e: Throwable) {
            if (e is HttpException) {
                return when (e.code()) {
                    // create exception from the error code
                    401, 404 ->
                        throw MovieException(e.message(), e.code(), ErrorType.SERVICE)
                    // create exception from network error
                    else -> throw MovieException(e.message(), e.code(), ErrorType.NETWORK)
                }
            }
            throw MovieException(e.message.orEmpty(), -1, ErrorType.UNKNOWN)
        }
    }


    suspend fun getMovieDetails(movieID: String): MovieDetails {
        try {
            updateConfigIfNeeded()
            return movieService.getMovieDetails(movieID)
        } catch (e: Throwable) {
            if (e is HttpException) {
                return when (e.code()) {
                    // create exception from the error code
                    401, 404 ->
                        throw MovieException(e.message(), e.code(), ErrorType.SERVICE)
                    // create exception from network error
                    else -> throw MovieException(e.message(), e.code(), ErrorType.NETWORK)
                }
            }
            throw MovieException(e.message.orEmpty(), -1, ErrorType.UNKNOWN)
        }

    }

    fun generateFullPosterPath(
        serviceConfig: ServiceConfig,
        posterEndURL: String
    ) = serviceConfig.images.secureBaseURL + serviceConfig.images.posterSizes.last() + posterEndURL


    companion object {
        const val SERVICE_CONFIG_CACHE = "SERVICE_CONFIG_CACHE"
        const val SERVICE_CONFIG_CACHE_AGE_DAYS = 7
    }
}