package com.example.movieapp.common.data.cache

import com.example.movieapp.common.data.cache.daos.MoviesDao
import com.example.movieapp.common.data.cache.model.CachedMovie
import com.example.movieapp.common.data.cache.model.relations.MoviesWithPlaylist
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomCache @Inject constructor(private val moviesDao: MoviesDao) : Cache {
    override suspend fun storeMovies(movies: List<CachedMovie>) {
        moviesDao.insertAnimalAggregate(movies)
    }

    override fun getMovies(): Flow<List<MoviesWithPlaylist>> {
        return moviesDao.getAllMovies()
    }

    override fun getMoviesByPlaylist(playlistName: String): Flow<List<MoviesWithPlaylist>> {
        return moviesDao.getMoviesByPlaylist(playlistName)
    }
}