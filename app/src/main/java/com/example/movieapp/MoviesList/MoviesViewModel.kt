package com.example.movieapp.MoviesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.MoviesList.presentation.MovieListEvent
import com.example.movieapp.MoviesList.presentation.MovieListState
import com.example.movieapp.MoviesList.usecases.GetMovies
import com.example.movieapp.MoviesList.usecases.RequestMoviesFromNetwork
import com.example.movieapp.common.domain.model.NetworkException
import com.example.movieapp.common.domain.model.NetworkUnavailableException
import com.example.movieapp.common.domain.model.movies.Movies
import com.example.movieapp.common.presentation.Event
import com.example.movieapp.common.utils.Logger
import com.example.movieapp.common.utils.createExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovies: GetMovies,
    private val requestMoviesFromNetwork: RequestMoviesFromNetwork
) : ViewModel() {
    val state: LiveData<MovieListState> get() = _state
    var isLoadingMoreMovies: Boolean = false
    private val _state = MutableLiveData<MovieListState>()

    init {
        _state.value = MovieListState()
        subscribeToMovieUpdates()
    }

    private fun subscribeToMovieUpdates() {
        viewModelScope.launch {
            try {
                getMovies().flowOn(Dispatchers.IO).collect {
                    onNewMovieList(it)
                }
            } catch (e: Throwable) {
                onFailure(e)
            }
        }
    }

    private fun onNewMovieList(movies: List<Movies>) {

        val currentList = state.value!!.movies
        val newMovies = movies.subtract(currentList)
        val updatedList = currentList + newMovies

        _state.value = state.value!!.copy(loading = false, movies = updatedList)
    }

    private fun onFailure(failure: Throwable) {
        when (failure) {
            is NetworkException,
            is NetworkUnavailableException -> {
                _state.value = state.value!!.copy(
                    loading = false,
                    failure = Event(failure)
                )
            }
        }
    }

    fun onEvent(event: MovieListEvent) {
        when (event) {
            is MovieListEvent.RequestInitialListOfMovies -> loadMovies()

        }
    }

    private fun loadMovies() {
        if (state.value!!.movies.isEmpty()) {
            fetchMovies()
        }
    }

    private fun fetchMovies() {
        isLoadingMoreMovies = true
        val errorMessage = "Failed to fetch Movies"
        val exceptionHandler = viewModelScope.createExceptionHandler(errorMessage) { onFailure(it) }
        viewModelScope.launch(exceptionHandler) {
            withContext(Dispatchers.IO) {
                Logger.d("Requesting more animals.")

                requestMoviesFromNetwork()
            }
            isLoadingMoreMovies = false

        }
    }
}


