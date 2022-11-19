package com.example.movieapp.MoviesList.usecases

import com.example.movieapp.common.domain.repository.MovieRepository
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class GetMovies @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke() = movieRepository.getMovies()
        .filter { it.isNotEmpty() }
}