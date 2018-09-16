package com.example.leonardoallen.guiabolso

import android.app.Application
import com.example.leonardoallen.guiabolso.di.AppComponent
import com.example.leonardoallen.guiabolso.di.AppModule
import com.example.leonardoallen.guiabolso.di.DaggerAppComponent

class GBApplication: Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()
    }

    fun setup() {
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): AppComponent {
        return component
    }

    companion object {
        lateinit var instance: GBApplication private set
    }

}