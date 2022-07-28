package app.mozarty.movies.ui.navigation


object NavigationArguments {
    const val movieID = "movieID"
}


object NavigationDestinations {
    const val discoverDestination = "discover"
    const val detailsDestinationTemplate = "details/"
    const val detailsDestination = "details/{${NavigationArguments.movieID}}"
}


