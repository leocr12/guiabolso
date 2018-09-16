package com.example.leonardoallen.guiabolso.category

import com.example.leonardoallen.guiabolso.api.ChuckAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoryPresenter: CategoryContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ChuckAPI = ChuckAPI.create()
    private lateinit var view: CategoryContract.View

    override fun getCategories() {
        view.showProgress()
        val subscribe = api.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ categories: List<String> ->
                    view.showCategories(categories)
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

    override fun attach(view: CategoryContract.View) {
        this.view = view
    }
}