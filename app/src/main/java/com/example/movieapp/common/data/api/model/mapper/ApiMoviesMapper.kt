package com.example.movieapp.common.data.api.model.mapper

import com.example.movieapp.common.data.api.model.ApiMovies
import com.example.movieapp.common.domain.model.movies.Movies

class ApiMoviesMapper:ApiMapper<ApiMovies,Movies>{
    override fun mapToDomain(apiEntity: ApiMovies): Movies {
        return Movies(title = apiEntity.title, rating = apiEntity.voteAverage, playlist = null,id =apiEntity.id, imageUrl = apiEntity.posterPath)
    }

}