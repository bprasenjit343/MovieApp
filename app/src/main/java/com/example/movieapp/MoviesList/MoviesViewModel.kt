package com.example.movieapp.MoviesList

import androidx.lifecycle.ViewModel
import com.example.movieapp.MoviesList.usecases.GetMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMovies: GetMovies) : ViewModel() {
}