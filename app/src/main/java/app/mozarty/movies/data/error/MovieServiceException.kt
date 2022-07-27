package app.mozarty.movies.data.error

data class MovieServiceException(override val message: String, val code: Int) : Exception()
