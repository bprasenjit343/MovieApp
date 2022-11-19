package com.example.movieapp.common.data.api.model


import com.google.gson.annotations.SerializedName

data class ApiPaginatedMovies(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<ApiMovies>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)