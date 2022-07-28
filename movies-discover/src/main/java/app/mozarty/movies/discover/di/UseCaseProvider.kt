package app.mozarty.movies.discover.di

import app.mozarty.movies.data.repository.MovieRepository
import app.mozarty.movies.discover.usecases.ListMoviesUseCaseImpl
import app.mozarty.movies.usecases.ListMoviesUseCase
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
    fun provideListMoviesUseCase(movieRepository: MovieRepository): ListMoviesUseCase {
        return ListMoviesUseCaseImpl(movieRepository)
    }


}