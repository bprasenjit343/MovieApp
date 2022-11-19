package com.example.movieapp.common.data.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.common.data.cache.Cache
import com.example.movieapp.common.data.cache.MovieDatabase
import com.example.movieapp.common.data.cache.RoomCache
import com.example.movieapp.common.data.cache.daos.MoviesDao
import com.example.movieapp.common.utils.getJsonFromAssets
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache: RoomCache): Cache

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context
        ): MovieDatabase {
            return Room.databaseBuilder(
                context,
                MovieDatabase::class.java,
                "movies.db"
            )
                .build()
        }

        @Provides
        fun provideAnimalsDao(
            movieDatabase: MovieDatabase
        ): MoviesDao = movieDatabase.moviesDao()

        @Provides
        fun providesSampleData(@ApplicationContext context: Context):String{
            return getJsonFromAssets(context,"Movies.json")
        }

    }
}