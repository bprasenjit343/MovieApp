package com.example.movieapp.common.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CachedMovie(
    val title: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val releaseDate: String,
    val rating: Double,
    val image: String,
    val overView: String
)