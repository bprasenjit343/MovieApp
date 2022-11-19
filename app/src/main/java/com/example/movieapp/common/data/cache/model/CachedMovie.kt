package com.example.movieapp.common.data.cache.model

import androidx.room.PrimaryKey
import java.util.Date

data class CachedMovie(
    val title:String,
    val popularity:Double,
    @PrimaryKey
    val id: Int ,
    val releaseDate: Date,
    val rating: Double,
    val image:String,
    val overView: String
)