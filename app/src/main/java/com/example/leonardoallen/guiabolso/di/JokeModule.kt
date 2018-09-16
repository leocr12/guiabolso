package com.example.leonardoallen.guiabolso.di

import android.app.Activity
import com.example.leonardoallen.guiabolso.category.CategoryContract
import com.example.leonardoallen.guiabolso.category.CategoryPresenter
import com.example.leonardoallen.guiabolso.joke.JokeContract
import com.example.leonardoallen.guiabolso.joke.JokePresenter
import dagger.Module
import dagger.Provides

@Module
class JokeModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): JokeContract.Presenter {
        return JokePresenter()
    }
}