package com.example.movieapp.common.data.cache.model.relations

import androidx.room.Entity


    @Entity(primaryKeys = ["id", "playlistName"])
    data class MoviesPlaylistCrossRef(
        val id: Int,
        val playlistName: String
    )
