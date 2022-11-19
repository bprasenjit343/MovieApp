package com.example.movieapp.common.data.cache

import com.example.movieapp.common.data.cache.model.CachedMovie
import kotlinx.coroutines.flow.Flow

interface Cache {

    suspend fun storeMovies(movies:List<CachedMovie>)
    fun getMovies(): Flow<List<CachedMovie>>
}