package com.example.leonardoallen.guiabolso.di

import com.example.leonardoallen.guiabolso.category.CategoryActivity
import com.example.leonardoallen.guiabolso.joke.JokeActivity
import dagger.Component

@Component(modules = [(JokeModule::class)])
interface JokeComponent {

    fun inject(jokeActivity: JokeActivity)
}