package com.example.compose.navigation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ImpaleApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}