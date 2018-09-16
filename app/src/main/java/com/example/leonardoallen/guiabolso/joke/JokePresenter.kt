package com.example.leonardoallen.guiabolso.joke

import com.example.leonardoallen.guiabolso.api.ChuckAPI
import com.example.leonardoallen.guiabolso.category.CategoryContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class JokePresenter: JokeContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ChuckAPI = ChuckAPI.create()
    private lateinit var view: JokeContract.View

    override fun getRandomJoke() {
        view.showProgress()
        val subscribe = api.getRandomJoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ joke: Joke ->
                    view.showJoke(joke)
                }, { error ->
                    view.showError(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun getRandomJokeFromCategory(category: String) {
        view.showProgress()
        val subscribe = api.getRandomJokeFromCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ joke: Joke ->
                    view.showJoke(joke)
                }, { error ->
                    view.showError(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: JokeContract.View) {
        this.view = view
    }
}