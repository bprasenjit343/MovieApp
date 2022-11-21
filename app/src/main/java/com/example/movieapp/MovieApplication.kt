package com.example.movieapp

import android.app.Application
import com.example.movieapp.common.utils.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initLogger()
    }

    private fun initLogger() {
        Logger.init()
    }
}