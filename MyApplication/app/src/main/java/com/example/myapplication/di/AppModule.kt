package com.example.myapplication.di

import com.example.myapplication.App
import com.example.myapplication.model.MediaProvider
import com.example.myapplication.model.Provider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {
    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun provideMediaProvider(): Provider =
        MediaProvider
}