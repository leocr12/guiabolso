package com.example.leonardoallen.guiabolso.category

import com.example.leonardoallen.guiabolso.BaseContract

interface CategoryContract {

    interface View: BaseContract.View {
        fun showError(error: String)
        fun showCategories(categories: List<String>)
        fun showProgress()
    }

    interface Presenter: BaseContract.Presenter<CategoryContract.View> {
        fun getCategories()
    }
}