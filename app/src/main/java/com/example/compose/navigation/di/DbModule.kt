package com.example.compose.navigation.di

import com.example.compose.navigation.ui.list.MovieGenerator
import com.example.compose.navigation.ui.list.MovieProvider
import com.example.compose.navigation.ui.list.MovieProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideMovieListProvider(movieGenerator: MovieGenerator): MovieProvider {
        return MovieProviderImpl(movieGenerator = movieGenerator)
    }
}