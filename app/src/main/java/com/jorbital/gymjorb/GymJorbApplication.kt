package com.jorbital.gymjorb

import android.app.Application
import timber.log.Timber

class GymJorbApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}