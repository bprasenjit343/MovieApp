package com.example.movieapp.common.data.cache.model

data class Playlist(
    val name: String,
    val movies: List<CachedMovie>
)