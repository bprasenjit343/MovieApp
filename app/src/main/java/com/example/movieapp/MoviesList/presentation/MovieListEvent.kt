package com.example.movieapp.MoviesList.presentation

sealed class MovieListEvent {

    object RequestInitialListOfMovies : MovieListEvent()

}
