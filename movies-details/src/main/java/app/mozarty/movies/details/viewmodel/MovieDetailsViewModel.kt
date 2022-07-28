package app.mozarty.movies.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mozarty.movies.data.dto.MovieDetails
import app.mozarty.movies.usecases.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase

) : ViewModel() {

    private val detailsViewState = MutableLiveData(ViewState.Loading)
    private val currentMovieDetails: MutableLiveData<MovieDetails?> = MutableLiveData(null)

    private var lastMovieRequestedID = ""

    enum class ViewState {
        Success, Loading, Error
    }


    fun getDetailsViewState(): LiveData<ViewState> {
        return detailsViewState
    }


    fun getMovieDetails(): MutableLiveData<MovieDetails?> {
        return currentMovieDetails
    }

    fun currentMovieUpdated(movieID: String) {
        if (movieID == currentMovieDetails.value?.id) {//no update needed
            currentMovieDetails.postValue(currentMovieDetails.value)
            detailsViewState.postValue(ViewState.Success)
        } else if (movieID == lastMovieRequestedID && detailsViewState.value != ViewState.Error) {
            //Request in progress
        } else {
            currentMovieDetails.postValue(null)
            detailsViewState.postValue(ViewState.Loading)
            lastMovieRequestedID = movieID
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


}