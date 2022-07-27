package app.mozarty.movies.di

import app.mozarty.movies.BuildConfig
import app.mozarty.movies.data.service.ConfigService
import app.mozarty.movies.data.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RetrofitProvider {


    @Provides
    @Singleton
    fun provideOKHttp(): OkHttpClient.Builder {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        else
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)


    }

    @Provides
    @Singleton
    fun provideMovieService(okHttpClient: OkHttpClient.Builder): MovieService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build().create(MovieService::class.java)

    }

    @Provides
    @Singleton
    fun provideConfigService(
        okHttpClient: OkHttpClient.Builder,
        cacheInterceptor: Interceptor
    ): ConfigService {
        okHttpClient.addNetworkInterceptor(cacheInterceptor)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build().create(ConfigService::class.java)
    }


    @Provides
    @Singleton
    fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response: Response = chain.proceed(chain.request())
            val cacheControl: CacheControl = CacheControl.Builder()
                .maxAge(cacheAge, TimeUnit.DAYS)
                .build()
            response.newBuilder()
                .header(cacheControlHeader, cacheControl.toString())
                .build()
        }
    }

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val cacheControlHeader = "Cache-Control"
        const val cacheAge = 7
    }
}