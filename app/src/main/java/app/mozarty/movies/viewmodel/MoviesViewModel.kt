package app.mozarty.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mozarty.movies.details.data.dto.MovieDetails
import app.mozarty.movies.details.data.usecases.GetMovieDetailsUseCase
import app.mozarty.movies.discover.data.dto.MovieOutline
import app.mozarty.movies.discover.data.usecases.ListMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val listMoviesUseCase: ListMoviesUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase

) : ViewModel() {

    private val discoverViewState = MutableLiveData(ViewState.Loading)
    private val detailsViewState = MutableLiveData(ViewState.Loading)
    private val moviesList: MutableLiveData<List<MovieOutline>> = MutableLiveData(emptyList())
    private val currentMovieDetails: MutableLiveData<MovieDetails?> = MutableLiveData(null)

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

    fun getDetailsViewState(): LiveData<ViewState> {
        return detailsViewState
    }

    fun getMovieList(): LiveData<List<MovieOutline>> {
        return moviesList
    }

    fun getMovieDetails(): MutableLiveData<MovieDetails?> {
        return currentMovieDetails
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

    fun getMovieDetails(movieID: String) {
        currentMovieDetails.postValue(null)
        detailsViewState.postValue(ViewState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            getMovieDetailsUseCase.invoke(movieID).onSuccess {
                currentMovieDetails.postValue(it)
                detailsViewState.postValue(ViewState.Success)
            }.onFailure {
                detailsViewState.postValue(ViewState.Error)
            }
        }
    }


}