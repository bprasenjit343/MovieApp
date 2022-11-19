package com.example.movieapp.common.data.api.model.mapper

import com.example.movieapp.common.data.api.model.ApiMovies
import com.example.movieapp.common.domain.model.movies.Movies
import javax.inject.Inject

class ApiMoviesMapper @Inject constructor(): ApiMapper<ApiMovies, Movies> {
    override fun mapToDomain(apiEntity: ApiMovies): Movies {
        return Movies(
            title = apiEntity.title,
            rating = apiEntity.voteAverage,
            playList = emptyList(),
            id = apiEntity.id,
            imageUrl = apiEntity.posterPath,
            releaseDate = apiEntity.releaseDate,
            overView = apiEntity.overview

        )
    }

}