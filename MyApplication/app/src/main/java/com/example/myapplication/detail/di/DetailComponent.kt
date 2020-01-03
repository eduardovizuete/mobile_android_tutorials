package com.example.myapplication.detail.di

import com.example.myapplication.detail.DetailActivity
import dagger.Subcomponent

@Subcomponent(modules = [(DetailModule::class)])
interface DetailComponent {
    fun inject(activity: DetailActivity)
}
