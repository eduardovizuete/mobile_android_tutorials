package com.example.myapplication.di

import com.example.myapplication.App
import com.example.myapplication.detail.di.DetailComponent
import com.example.myapplication.detail.di.DetailModule
import com.example.myapplication.main.di.MainComponent
import com.example.myapplication.main.di.MainModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(app: App)
    fun plus(homeModule: MainModule): MainComponent
    fun plus(detailModule: DetailModule): DetailComponent
}