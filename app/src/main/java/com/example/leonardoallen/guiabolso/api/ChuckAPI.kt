package com.example.leonardoallen.guiabolso.api

import com.example.leonardoallen.guiabolso.joke.Joke
import com.example.leonardoallen.guiabolso.util.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckAPI {

    @GET("jokes/random")
    fun getRandomJoke(): Observable<Joke>

    @GET("jokes/categories")
    fun getCategories(): Observable<List<String>>

    @GET("jokes/random")
    fun getRandomJokeFromCategory(@Query("category") category: String): Observable<Joke>

    companion object Factory {
        fun create(): ChuckAPI {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build()

            return retrofit.create(ChuckAPI::class.java)
        }
    }
}