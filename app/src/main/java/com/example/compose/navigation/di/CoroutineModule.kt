package com.example.compose.navigation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import kotlin.coroutines.CoroutineContext

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelCoroutineContext

@Module
@InstallIn(ViewModelComponent::class)
object CoroutineModule {

    @Provides
    @ViewModelCoroutineContext
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.Default
    }
}