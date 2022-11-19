package com.example.movieapp.common.domain.repository

import com.example.movieapp.common.domain.model.movies.Movies
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movies>>
    suspend fun storeMovies(movies: List<Movies>)
    suspend fun filterMoviesByPlaylist(name:String):List<Movies>
}