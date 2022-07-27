package app.mozarty.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mozarty.movies.data.dto.MovieOutline
import app.mozarty.movies.data.usecases.ListMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val listMoviesUseCase: ListMoviesUseCase

) : ViewModel() {

    enum class ViewState {
        Success, Loading, Error
    }

    private val viewState = MutableLiveData(ViewState.Loading)

    private val moviesList: MutableLiveData<List<MovieOutline>> = MutableLiveData(emptyList())

    val currentPage: Int = 1
    val maxPages: Int = 1


    fun getViewState(): LiveData<ViewState> {
        return viewState
    }

}