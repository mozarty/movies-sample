package app.mozarty.movies.data.dto

import com.google.gson.annotations.SerializedName

data class MovieListResultsPage(
    val page: Int,
    val results: List<MovieOutline>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
