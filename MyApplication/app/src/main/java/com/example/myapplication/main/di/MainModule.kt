package com.example.myapplication.main.di

import com.example.myapplication.main.MainPresenter
import com.example.myapplication.model.Provider
import com.example.myapplication.main.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val activity: MainActivity) {
    @Provides
    fun provideMainPresenter(provider: Provider) =
        MainPresenter(activity, provider)
}
