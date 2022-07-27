package app.mozarty.movies.data.dto

import com.google.gson.annotations.SerializedName

data class ImageConfig(
    @SerializedName("base_url")
    val baseURL: String,
    @SerializedName("secure_base_url")
    val secureBaseURL: String,
    @SerializedName("poster_sizes")
    val posterSizes: List<String>,
)