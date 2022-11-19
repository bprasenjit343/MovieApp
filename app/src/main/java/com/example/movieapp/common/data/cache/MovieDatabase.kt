package com.example.movieapp.common.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.common.data.cache.daos.MoviesDao
@Database(
    entities = [
    ],
    version = 1
)
abstract class MovieDatabase:RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
}