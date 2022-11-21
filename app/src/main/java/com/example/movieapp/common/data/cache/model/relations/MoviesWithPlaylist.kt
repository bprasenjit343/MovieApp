package com.example.movieapp.common.data.cache.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.movieapp.common.data.cache.model.CachedMovie
import com.example.movieapp.common.data.cache.model.Playlist
import com.example.movieapp.common.domain.model.movies.Movies

data class MoviesWithPlaylist (
    @Embedded val movie: CachedMovie,
    @Relation(
        parentColumn = "id",
        entityColumn = "playlistName",
        associateBy = Junction(MoviesPlaylistCrossRef::class)
    )
    val playlist: List<Playlist> = emptyList()
){
    companion object {
        fun fromDomain(movie: Movies): CachedMovie {
            return CachedMovie(title = movie.title,
                    id = movie.id,
                    releaseDate = movie.releaseDate,
                    rating = movie.rating,
                    image = movie.imageUrl,
                    overView = movie.overView)

        }
        fun toDomain(movieAggregate: MoviesWithPlaylist):Movies{
            return Movies(title = movieAggregate.movie.title,
                id = movieAggregate.movie.id,
                releaseDate = movieAggregate.movie.releaseDate,
                rating = movieAggregate.movie.rating,
                imageUrl = movieAggregate.movie.image,
                overView = movieAggregate.movie.overView,
                playList = movieAggregate.playlist.map { it.playlistName } )
        }
    }
}