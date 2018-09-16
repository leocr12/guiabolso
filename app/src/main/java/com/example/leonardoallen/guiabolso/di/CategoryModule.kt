package com.example.leonardoallen.guiabolso.di

import android.app.Activity
import com.example.leonardoallen.guiabolso.category.CategoryContract
import com.example.leonardoallen.guiabolso.category.CategoryPresenter
import dagger.Module
import dagger.Provides

@Module
class CategoryModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): CategoryContract.Presenter {
        return CategoryPresenter()
    }
}