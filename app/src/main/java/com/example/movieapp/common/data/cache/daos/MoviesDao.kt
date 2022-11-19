package com.example.movieapp.common.data.cache.daos

import androidx.room.*
import com.example.movieapp.common.data.cache.model.CachedMovie
import com.example.movieapp.common.data.cache.model.relations.MoviesWithPlaylist
import kotlinx.coroutines.flow.Flow


@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAnimalAggregate(movies: List<CachedMovie>)

    @Transaction
    @Query("SELECT * FROM CachedMovie")
    abstract fun getAllMovies(): Flow<List<MoviesWithPlaylist>>

    @Query("""SELECT * from MoviesPlaylistCrossRef as c join CachedMovie as p on c.id = p.id where playlistName =:playListName""")
    abstract fun getMoviesByPlaylist(playListName: String): Flow<List<MoviesWithPlaylist>>
}