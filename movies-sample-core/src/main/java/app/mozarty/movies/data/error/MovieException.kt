package app.mozarty.movies.data.error

data class MovieException(
    override val message: String,
    val code: Int,
    val errorType: ErrorType
) : Exception()
