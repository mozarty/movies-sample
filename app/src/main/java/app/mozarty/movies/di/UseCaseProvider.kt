package app.mozarty.movies.di

import app.mozarty.movies.data.repository.MovieRepository
import app.mozarty.movies.data.usecases.GetMovieDetailsUseCase
import app.mozarty.movies.data.usecases.GetMovieDetailsUseCaseImpl
import app.mozarty.movies.data.usecases.ListMoviesUseCase
import app.mozarty.movies.data.usecases.ListMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class UseCaseProvider {


    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(movieRepository: MovieRepository): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCaseImpl(movieRepository)
    }

    @Provides
    @Singleton
    fun provideListMoviesUseCase(movieRepository: MovieRepository): ListMoviesUseCase {
        return ListMoviesUseCaseImpl(movieRepository)
    }


}