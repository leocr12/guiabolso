package com.example.leonardoallen.guiabolso.category

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.leonardoallen.guiabolso.di.CategoryModule
import com.example.leonardoallen.guiabolso.R
import com.example.leonardoallen.guiabolso.di.DaggerCategoryComponent
import com.example.leonardoallen.guiabolso.joke.JokeActivity
import kotlinx.android.synthetic.main.activity_category.*
import javax.inject.Inject

class CategoryActivity : AppCompatActivity(), CategoryContract.View, CategoryAdapter.CategoryCallback {

    @Inject
    lateinit var presenter: CategoryContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        injectDependency()

        presenter.attach(this)

        presenter.getCategories()

        randomJokeButton.setOnClickListener {
            val intent = Intent(this, JokeActivity::class.java)
            intent.putExtra("category", "")
            startActivity(intent)
        }
    }

    private fun injectDependency() {
        val activityComponent = DaggerCategoryComponent.builder()
                .categoryModule(CategoryModule(this))
                .build()

        activityComponent.inject(this)
    }

    override fun showProgress() {
        categoryProgress.visibility = View.VISIBLE
    }

    override fun showError(error: String) {
        categoryProgress.visibility = View.INVISIBLE
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showCategories(categories: List<String>) {
        categoryProgress.visibility = View.INVISIBLE
        categoryRecycler.layoutManager = LinearLayoutManager(this)
        categoryRecycler.adapter = CategoryAdapter(categories, this)
    }

    override fun onCategoryClick(category: String) {
        val intent = Intent(this, JokeActivity::class.java)
        intent.putExtra("category", category)
        startActivity(intent)
    }
}