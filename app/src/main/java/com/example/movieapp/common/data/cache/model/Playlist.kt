package com.example.movieapp.common.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Playlist(
    @PrimaryKey(autoGenerate = false)
    val playlistName: String
)