package app.mozarty.movies.di

import app.mozarty.movies.BuildConfig
import app.mozarty.movies.data.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RetrofitProvider {


    @Provides
    @Singleton
    fun provideOKHttp(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        else
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
            .build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    }


    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)

    }

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"

    }
}