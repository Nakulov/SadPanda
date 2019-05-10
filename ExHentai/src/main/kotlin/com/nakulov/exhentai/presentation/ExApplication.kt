package com.nakulov.exhentai.presentation

import android.app.Application
import com.nakulov.exhentai.BuildConfig
import timber.log.Timber

class ExApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeTimber()
    }

    private fun initializeTimber() {
        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else AnalyticsReporterTree())
    }

    internal inner class AnalyticsReporterTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

        }
    }
}