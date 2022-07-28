package app.mozarty.movies.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.mozarty.movies.data.cache.CacheManager
import app.mozarty.movies.data.error.MovieException
import app.mozarty.movies.data.service.ConfigService
import app.mozarty.movies.data.service.MovieService
import app.mozarty.movies.mock.Mocks
import io.kotlintest.mock.`when`
import io.kotlintest.mock.mock
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.any
import org.mockito.kotlin.verify


class MovieRepositoryTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()


    @Test
    fun `should return movie details success`() = runTest {
        val movieService: MovieService = mock()
        val configService: ConfigService = mock()
        val cacheManager: CacheManager = mock()
        val movieRepository = MovieRepository(movieService, configService, cacheManager)

        `when`(
            movieService.getMovieDetails(
                any(),
                any(),
                any()
            )
        ).thenReturn(Mocks.sampleMovieDetails)

        val result = movieRepository.getMovieDetails("123")


        verify(movieService).getMovieDetails(any(), any(), any())

        assert(result.id == Mocks.sampleMovieDetails.id)
    }

    @Test
    fun `should return movie list success`() = runTest {
        val movieService: MovieService = mock()
        val configService: ConfigService = mock()
        val cacheManager: CacheManager = mock()
        val movieRepository = MovieRepository(movieService, configService, cacheManager)

        `when`(
            movieService.listMovies(
                any(),
                any(),
                any()
            )
        ).thenReturn(Mocks.sampleMoviePage)

        val result = movieRepository.listMovies(1)


        verify(movieService).listMovies(any(), any(), any())

        assert(result.totalPages == Mocks.sampleMoviePage.totalPages)
    }

    @Test
    fun `should throw MovieException from movieDetails`() = runTest {
        val movieService: MovieService = mock()
        val configService: ConfigService = mock()
        val cacheManager: CacheManager = mock()
        val movieRepository = MovieRepository(movieService, configService, cacheManager)

        `when`(
            movieService.getMovieDetails(
                any(),
                any(),
                any()
            )
        ).thenThrow()

        try {
            movieRepository.getMovieDetails("123")
        } catch (e: Throwable) {
            assert(e is MovieException)
        }

    }

    @Test
    fun `should throw MovieException from movieList`() = runTest {
        val movieService: MovieService = mock()
        val configService: ConfigService = mock()
        val cacheManager: CacheManager = mock()
        val movieRepository = MovieRepository(movieService, configService, cacheManager)

        `when`(
            movieService.listMovies(
                any(),
                any(),
                any()
            )
        ).thenThrow()

        try {
            movieRepository.listMovies(1)
        } catch (e: Throwable) {
            assert(e is MovieException)
        }

    }


}
