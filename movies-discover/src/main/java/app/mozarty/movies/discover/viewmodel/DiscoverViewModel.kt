package app.mozarty.movies.discover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mozarty.movies.data.dto.MovieOutline
import app.mozarty.movies.usecases.ListMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val listMoviesUseCase: ListMoviesUseCase
) : ViewModel() {

    private val discoverViewState = MutableLiveData(ViewState.Loading)
    private val moviesList: MutableLiveData<List<MovieOutline>> = MutableLiveData(emptyList())

    var currentPage: Int = 1
    var maxPages: Int = 1

    init {
        reLoadMoviesList()
    }

    enum class ViewState {
        Success, Loading, Error
    }


    fun getDiscoverViewState(): LiveData<ViewState> {
        return discoverViewState
    }


    fun getMovieList(): LiveData<List<MovieOutline>> {
        return moviesList
    }

    private fun reLoadMoviesList() {
        discoverViewState.postValue(ViewState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            listMoviesUseCase.invoke(currentPage).onSuccess {
                currentPage = it.page
                maxPages = it.totalPages
                moviesList.postValue(it.results)
                discoverViewState.postValue(ViewState.Success)
            }.onFailure {
                discoverViewState.postValue(ViewState.Error)
            }
        }
    }


}