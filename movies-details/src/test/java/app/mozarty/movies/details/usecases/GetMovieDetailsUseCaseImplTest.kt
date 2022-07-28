package app.mozarty.movies.details.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.mozarty.movies.data.error.ErrorType
import app.mozarty.movies.data.error.MovieException
import app.mozarty.movies.data.repository.MovieRepository
import io.kotlintest.mock.`when`
import io.kotlintest.mock.mock
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.any

class GetMovieDetailsUseCaseImplTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()


    @Test
    fun ` movie details should return success`() = runTest {
        val movieRepository: MovieRepository = mock()

        `when`(
            movieRepository.getMovieDetails(any())
        ).thenReturn(mock())

        val listMoviesUseCaseImpl = GetMovieDetailsUseCaseImpl(movieRepository)

        val result = listMoviesUseCaseImpl.invoke("123")

        assert(result.isSuccess)

    }

    @Test
    fun `list movies should fail in case of exception`() = runTest {
        val movieRepository: MovieRepository = mock()

        `when`(
            movieRepository.getMovieDetails(any())
        ).thenThrow(MovieException("", 1, ErrorType.NETWORK))

        val listMoviesUseCaseImpl = GetMovieDetailsUseCaseImpl(movieRepository)

        val result = listMoviesUseCaseImpl.invoke("123")

        assert(result.isFailure)

    }

}