package com.gcash.weatherapp.core.di

import com.google.android.gms.tasks.CancellationTokenSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {
    @Provides
    fun provideCancellationTokenSource() = CancellationTokenSource()
}