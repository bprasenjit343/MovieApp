package com.example.movieapp.common.di

import com.example.movieapp.common.data.ImdbMoviesRepository
import com.example.movieapp.common.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    @ActivityRetainedScoped
    abstract fun bindMovieRepository(repository: ImdbMoviesRepository): MovieRepository
}