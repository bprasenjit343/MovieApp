package com.example.movieapp.common.data

import com.example.movieapp.common.data.api.ImdbApi
import com.example.movieapp.common.data.api.model.ApiPaginatedMovies
import com.example.movieapp.common.data.api.model.mapper.ApiMoviesMapper
import com.example.movieapp.common.data.cache.Cache
import com.example.movieapp.common.data.cache.model.relations.MoviesWithPlaylist
import com.example.movieapp.common.domain.model.movies.Movies
import com.example.movieapp.common.domain.repository.MovieRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.lang.reflect.Type
import javax.inject.Inject


class ImdbMoviesRepository @Inject constructor(
   // private val imdbApi:ImdbApi,
    private val cache:Cache,
    private val sampleData:String,
    private val apiMoviesMapper: ApiMoviesMapper
):MovieRepository {
    override fun getMovies(): Flow<List<Movies>> {
       return cache.getMovies().distinctUntilChanged().map { moviesWithPlaylist-> moviesWithPlaylist.map { movie-> MoviesWithPlaylist.toDomain(movie) } }
    }

    override suspend fun storeMovies(movies: List<Movies>) {
      val moviesToStore = movies.map {movie-> MoviesWithPlaylist.fromDomain(movie) }
        cache.storeMovies(moviesToStore)
    }

    override suspend fun filterMoviesByPlaylist(name: String): Flow<List<Movies>> {
       return cache.getMoviesByPlaylist(name).distinctUntilChanged().map { movies-> movies.map { it-> MoviesWithPlaylist.toDomain(it) } }
    }

    override suspend fun fetchMoviesFromNetwork(): List<Movies> {
        val gson = Gson()
       //val listUserType = object : TypeToken<List<Movies?>?>() {}.type
        val movies = gson.fromJson(sampleData, ApiPaginatedMovies::class.java)
        return movies.results.map { it->apiMoviesMapper.mapToDomain(it) }
    }
}