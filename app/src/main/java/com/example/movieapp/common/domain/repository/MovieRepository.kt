package com.example.movieapp.common.domain.repository

import com.example.movieapp.common.data.api.model.ApiMovies
import com.example.movieapp.common.domain.model.movies.Movies
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movies>>
    suspend fun storeMovies(movies: List<Movies>)
    suspend fun filterMoviesByPlaylist(name:String):Flow<List<Movies>>
    suspend fun fetchMoviesFromNetwork():List<Movies>
}