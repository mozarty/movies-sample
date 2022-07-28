package app.mozarty.movies.details.di

import app.mozarty.movies.data.repository.MovieRepository
import app.mozarty.movies.details.usecases.GetMovieDetailsUseCaseImpl
import app.mozarty.movies.usecases.GetMovieDetailsUseCase
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


}