package com.example.myapplication.detail.di

import com.example.myapplication.detail.DetailPresenter
import com.example.myapplication.model.Provider
import com.example.myapplication.detail.DetailActivity
import dagger.Module
import dagger.Provides

@Module
class DetailModule(private val activity: DetailActivity) {
    @Provides
    fun provideDetailPresenter(provider: Provider) =
        DetailPresenter(activity, provider)
}
