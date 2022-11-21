package com.example.movieapp.common.domain.model.movies

import java.util.*

data class Movies(
    val title: String,
    val id: Int,
    val releaseDate: String,
    val rating: Double,
    val imageUrl: String,
    val overView: String,
    val playList: List<String>
)