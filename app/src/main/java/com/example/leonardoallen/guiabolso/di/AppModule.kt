package com.example.leonardoallen.guiabolso.di

import com.example.leonardoallen.guiabolso.GBApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val baseApp: GBApplication) {

    @Provides
    @Singleton
    fun provideApplication(): GBApplication {
        return baseApp
    }

}
