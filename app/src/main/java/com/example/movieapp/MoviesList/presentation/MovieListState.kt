package com.example.movieapp.MoviesList.presentation

import com.example.movieapp.common.domain.model.movies.Movies
import com.example.movieapp.common.presentation.Event

data class MovieListState(
    val loading: Boolean = true,
    val movies: List<Movies> = emptyList(),
    val failure: Event<Throwable>? = null,
    val noMoviesAvailable: Boolean = false,
)
