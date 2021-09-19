package com.example.baloot.alimehdizadeh

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App :Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}