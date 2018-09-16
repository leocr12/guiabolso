package com.example.leonardoallen.guiabolso.joke

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.leonardoallen.guiabolso.R
import com.example.leonardoallen.guiabolso.di.DaggerJokeComponent
import com.example.leonardoallen.guiabolso.di.JokeModule
import kotlinx.android.synthetic.main.activity_joke.*
import javax.inject.Inject

class JokeActivity : AppCompatActivity(), JokeContract.View {

    @Inject
    lateinit var presenter: JokeContract.Presenter

    var category: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke)

        if (intent.extras != null) {
            category = intent.extras.getString("category")
        }

        injectDependency()

        presenter.attach(this)

        if (category.isNotEmpty()) {
            presenter.getRandomJokeFromCategory(category)
        } else {
            presenter.getRandomJoke()
        }
    }

    private fun injectDependency() {
        val activityComponent = DaggerJokeComponent.builder()
                .jokeModule(JokeModule(this))
                .build()

        activityComponent.inject(this)
    }

    override fun showProgress() {
        jokeProgress.visibility = View.VISIBLE
    }

    override fun showError(error: String) {
        jokeProgress.visibility = View.INVISIBLE
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showJoke(joke: Joke) {
        jokeProgress.visibility = View.INVISIBLE
        Glide.with(this).load(joke.icon_url).into(jokeIcon)
        jokeText.text = joke.value
        if (category.isNotEmpty()) {
            jokeLink.text = Html.fromHtml("<a href=\"${joke.url}\">Categoria $category</a>")
        } else {
            jokeLink.text = Html.fromHtml("<a href=\"${joke.url}\">Categoria</a>")
        }
        jokeLink.movementMethod = LinkMovementMethod.getInstance()
    }

}
