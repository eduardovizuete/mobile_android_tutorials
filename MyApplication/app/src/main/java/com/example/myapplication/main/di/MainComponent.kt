package com.example.myapplication.main.di

import com.example.myapplication.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [(MainModule::class)])
interface MainComponent {
    fun inject(activity: MainActivity)
}
