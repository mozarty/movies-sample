package app.mozarty.movies.data.cache

import android.content.Context
import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.kotlintest.mock.`when`
import io.kotlintest.mock.mock
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class CacheManagerTest {


    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()


    @Test
    fun `should save cache in shared preferences only once`() = runTest {
        val applicationContext: Context = mock()
        val sharedPreferences: SharedPreferences = mock()
        val sharedPreferencesEditor: SharedPreferences.Editor = mock()

        `when`(
            applicationContext.getSharedPreferences(
                any(),
                any()
            )
        ).thenReturn(sharedPreferences)

        `when`(
            sharedPreferences.edit()
        ).thenReturn(sharedPreferencesEditor)

        val cacheManager = CacheManager(applicationContext)

        cacheManager.store("test", "testKey", 1)

        verify(sharedPreferencesEditor, times(1)).putString(any(), any())
        verify(sharedPreferencesEditor, times(1)).putLong(any(), any())

    }


    @Test
    fun `should expire cache if maxAge exceeded`() = runTest {
        val applicationContext: Context = mock()
        val sharedPreferences: SharedPreferences = mock()

        `when`(
            applicationContext.getSharedPreferences(
                any(),
                any()
            )
        ).thenReturn(sharedPreferences)

        `when`(
            sharedPreferences.getLong(any(), any())
        ).thenReturn(System.currentTimeMillis() - 1)


        val cacheManager = CacheManager(applicationContext)

        val result = cacheManager.get("testKey")

        assert(result.isFailure)

    }

    @Test
    fun `should return error if cache value is empty`() = runTest {
        val applicationContext: Context = mock()
        val sharedPreferences: SharedPreferences = mock()

        `when`(
            applicationContext.getSharedPreferences(
                any(),
                any()
            )
        ).thenReturn(sharedPreferences)

        `when`(
            sharedPreferences.getLong(any(), any())
        ).thenReturn(System.currentTimeMillis()+40000000)
        `when`(
            sharedPreferences.getString(any(), any())
        ).thenReturn("")


        val cacheManager = CacheManager(applicationContext)

        val result = cacheManager.get("testKey")

        assert(result.isFailure)

    }


    @Test
    fun `should return value cache if maxAge is in the Future`() = runTest {
        val applicationContext: Context = mock()
        val sharedPreferences: SharedPreferences = mock()

        `when`(
            applicationContext.getSharedPreferences(
                any(),
                any()
            )
        ).thenReturn(sharedPreferences)

        `when`(
            sharedPreferences.getLong(any(), any())
        ).thenReturn(System.currentTimeMillis()+40000000)
        `when`(
            sharedPreferences.getString(any(), any())
        ).thenReturn("testValue")


        val cacheManager = CacheManager(applicationContext)

        val result = cacheManager.get("testKey")

        assert(result.isSuccess)

    }

}