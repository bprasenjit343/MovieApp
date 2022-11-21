package com.example.movieapp.common.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.common.data.cache.daos.MoviesDao
import com.example.movieapp.common.data.cache.model.CachedMovie
import com.example.movieapp.common.data.cache.model.Playlist
import com.example.movieapp.common.data.cache.model.relations.MoviesPlaylistCrossRef
import com.example.movieapp.common.data.cache.model.relations.MoviesWithPlaylist

@Database(
    entities = [
        CachedMovie::class,
        Playlist::class,
        MoviesPlaylistCrossRef::class
    ],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
}