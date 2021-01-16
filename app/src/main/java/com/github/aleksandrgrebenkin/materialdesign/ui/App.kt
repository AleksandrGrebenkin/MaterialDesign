package com.github.aleksandrgrebenkin.materialdesign.ui

import android.app.Application
import com.github.aleksandrgrebenkin.materialdesign.di.AppComponent
import com.github.aleksandrgrebenkin.materialdesign.di.DaggerAppComponent
import com.github.aleksandrgrebenkin.materialdesign.di.modules.AppModule

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}