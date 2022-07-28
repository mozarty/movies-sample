package app.mozarty.movies.data.dto

import com.google.gson.annotations.SerializedName

data class MovieOutline(
    val id: String,
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genre_ids") val genreIDs: List<String>,
    @SerializedName("original_language") val language: String,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String,
    val popularity: String,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val score: String,
    @SerializedName("vote_count") val voteCount: String,
)
