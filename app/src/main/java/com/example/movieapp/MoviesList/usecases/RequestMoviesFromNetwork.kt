package com.example.movieapp.MoviesList.usecases

import com.example.movieapp.common.domain.model.NoMoviesAvailableException
import com.example.movieapp.common.domain.repository.MovieRepository
import javax.inject.Inject

class RequestMoviesFromNetwork @Inject constructor(private val movieRepository: MovieRepository,sampleData:String) {
    suspend operator fun invoke() {
        val movies = movieRepository.fetchMoviesFromNetwork()
        if (movies.isEmpty()) throw NoMoviesAvailableException("No movies Available :(")
        movieRepository.storeMovies(movies)
    }
}