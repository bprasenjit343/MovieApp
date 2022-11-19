package com.example.movieapp.common.domain.model.movies

data class Movies(
    val id: Int,
    val title: String,
    val rating: Double,
    val playlist: String?,
    val imageUrl:String
)