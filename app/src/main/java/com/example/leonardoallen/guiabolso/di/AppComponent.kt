package com.example.leonardoallen.guiabolso.di

import com.example.leonardoallen.guiabolso.GBApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(application: GBApplication)
}