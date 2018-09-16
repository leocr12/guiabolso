package com.example.leonardoallen.guiabolso.joke

import com.example.leonardoallen.guiabolso.BaseContract

interface JokeContract {

    interface View: BaseContract.View {
        fun showError(error: String)
        fun showJoke(joke: Joke)
        fun showProgress()
    }

    interface Presenter: BaseContract.Presenter<JokeContract.View> {
        fun getRandomJokeFromCategory(category: String)
        fun getRandomJoke()
    }
}