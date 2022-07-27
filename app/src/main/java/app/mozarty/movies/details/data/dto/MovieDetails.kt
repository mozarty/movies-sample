package app.mozarty.movies.details.data.dto

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    val id: String,
    val adult: Boolean,
    @SerializedName("vote_average") val score: String,
    @SerializedName("vote_count") val voteCount: String,
    val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("original_language") val language: String,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String,
    val popularity: String,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("release_date") val releaseDate: String,

    val genres: List<MovieGenre>,
    val budget: Int,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String
)
