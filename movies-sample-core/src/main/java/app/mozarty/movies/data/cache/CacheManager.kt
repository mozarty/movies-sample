package app.mozarty.movies.data.cache

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheManager @Inject constructor(@ApplicationContext private val applicationContext: Context) {

    private val sharedPreferences: SharedPreferences = applicationContext.getSharedPreferences(
        PREFERENCE_NAME, Context.MODE_PRIVATE
    );


    fun store(serializedValue: String, cacheKey: String, maxAgeDays: Int) {
        val editor = sharedPreferences.edit()
        editor.putString(cacheKey, serializedValue)
        editor.putLong(
            cacheKey + CACHE_MAX_AGE_IN_DAYS,
            System.currentTimeMillis() + daysToMillis(maxAgeDays)
        )
        editor.apply()
    }

    fun get(cacheKey: String): Result<String> {
        val cacheMagAge = sharedPreferences.getLong(cacheKey + CACHE_MAX_AGE_IN_DAYS, -1)
        if (cacheMagAge > System.currentTimeMillis()) {
            val value = sharedPreferences.getString(cacheKey, null)
            return if(value.isNullOrBlank()){
                Result.failure(EmptyStackException())
            }else{
                Result.success(value)
            }
        }
        return Result.failure(EmptyStackException())
    }

    private fun daysToMillis(maxAgeDays: Int): Long {
        return maxAgeDays.toLong() * 24 * 60 * 60 * 1000
    }


    companion object {
        const val PREFERENCE_NAME = "Cache"


        const val CACHE_MAX_AGE_IN_DAYS = "CACHE_MAX_AGE"
    }
}