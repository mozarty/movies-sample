@file:OptIn(ExperimentalCoroutinesApi::class)

package app.mozarty.movies.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.mozarty.movies.Mocks
import app.mozarty.movies.data.error.ErrorType
import app.mozarty.movies.data.error.MovieException
import app.mozarty.movies.data.repository.MovieRepository
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MoviesActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MoviesActivity>()

    @BindValue
    val movieRepository: MovieRepository = mock()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun shouldDisplayFiveMovieItemsOnSuccess() = runTest {
        whenever(movieRepository.listMovies(1)).thenReturn(Mocks.sampleMoviePageFiveItems)
        //assert error view not displayed
        composeRule.waitForIdle()
        composeRule.onNodeWithTag(TestTags.ErrorState).assertDoesNotExist()
        composeRule.onAllNodes(hasTestTag(TestTags.MovieListItem)).assertCountEquals(5)
    }


    @Test
    fun shouldDisplayErrorOnDiscoverFailure() = runTest {
        whenever(movieRepository.listMovies(any())).thenThrow(
            MovieException(
                "1",
                1,
                ErrorType.NETWORK
            )
        )
        //assert error view is displayed
        composeRule.waitForIdle()
        composeRule.onNodeWithTag(TestTags.ErrorState).assertIsDisplayed()
    }


    @Test
    fun shouldDisplayMovieDetailsOnSuccess() = runTest {
        whenever(movieRepository.listMovies(any())).thenReturn(Mocks.sampleMoviePageFiveItems)
        whenever(movieRepository.getMovieDetails(any())).thenReturn(Mocks.sampleMovieDetails)
        composeRule.waitForIdle()

        composeRule.onAllNodes(hasTestTag(TestTags.MovieListItem)).onFirst().performClick()

        composeRule.waitForIdle()
        composeRule.onNodeWithTag(TestTags.DetailsScreen).assertIsDisplayed()

        composeRule.onNodeWithTag(TestTags.MovieTitle)
            .assertTextContains(Mocks.sampleMovieDetails.title)
    }


    @Test
    fun shouldDisplayMovieDetailsErrorOnFail() = runTest {
        whenever(movieRepository.listMovies(any())).thenReturn(Mocks.sampleMoviePageFiveItems)
        whenever(movieRepository.getMovieDetails(any())).thenThrow(
            MovieException(
                "1",
                1,
                ErrorType.NETWORK
            )
        )
        composeRule.waitForIdle()

        composeRule.onAllNodes(hasTestTag(TestTags.MovieListItem)).onFirst().performClick()

        composeRule.waitForIdle()
        composeRule.onNodeWithTag(TestTags.DetailsScreen).assertDoesNotExist()

        composeRule.onNodeWithTag(TestTags.ErrorState).assertIsDisplayed()

    }


}
