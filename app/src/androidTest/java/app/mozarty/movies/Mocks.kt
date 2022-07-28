package app.mozarty.movies

import app.mozarty.movies.data.dto.MovieDetails
import app.mozarty.movies.data.dto.MovieListResultsPage
import app.mozarty.movies.data.dto.MovieOutline
import java.util.*

object Mocks {

    val sampleMovieDetails = MovieDetails(
        id = "",
        voteCount = "1000",
        video = false,
        title = "Wonder",
        score = "10.1",
        releaseDate = "December 1, 2017",
        posterPath = "",
        popularity = "100000",
        overview = "WONDER tells the incredibly inspiring and heartwarming story of August Pullman. Born with facial differences that, up until now, have prevented him from going to a mainstream school, Auggie becomes the most unlikely of heroes when he enters the local fifth grade. As his family, his new classmates, and the larger community all struggle to discover their compassion and acceptance, Auggie's extraordinary journey will unite them all and prove you can't blend in when you were born to stand out.",
        originalTitle = "Wonder",
        language = "en",
        backdropPath = "",
        adult = false,
        budget = 0,
        genres = emptyList(),
        revenue = 1,
        runtime = 100,
        status = "Released",
        tagline = "Choose kind"
    )

    private fun sampleMovieOutline() = MovieOutline(
        adult = false,
        backdropPath = "",
        genreIDs = emptyList(),
        id = UUID.randomUUID().toString(),
        language = "ar",
        originalTitle = "Scott Pilgrim Vs. The World",
        overview = "Scott Pilgrim meets Ramona and instantly falls in love with her. But when he meets one of her exes at a band competition, he realises that he has to deal with all seven of her exes to woo her.",
        popularity = "10000",
        posterPath = "",
        releaseDate = "October 21, 2010",
        score = "10",
        title = "Scott Pilgrim Vs. The World",
        video = false,
        voteCount = "10000"
    )

    val sampleMoviePageOneItem = MovieListResultsPage(
        page = 1, totalResults = 1, results = listOf(
            sampleMovieOutline()
        ), totalPages = 1
    )

    val sampleMoviePageFiveItems =
        MovieListResultsPage(
            page = 1, totalResults = 5, results = List(5) { sampleMovieOutline() }, totalPages = 1
        )


}