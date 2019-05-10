package com.nakulov.exhentai.presentation

import android.app.Application
import android.os.Handler
import android.os.Looper
import com.nakulov.exhentai.BuildConfig
import com.nakulov.exhentai.presentation.di.components.ApplicationComponent
import com.nakulov.exhentai.presentation.di.components.DaggerApplicationComponent
import com.nakulov.exhentai.presentation.di.modules.ApplicationModule
import timber.log.Timber

class ExApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeInjections()
        initializeTimber()
    }

    private fun initializeInjections() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    private fun initializeTimber() {
        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else AnalyticsReporterTree())
    }

    companion object {
        lateinit var applicationComponent: ApplicationComponent

        val applicationHandler = Handler(Looper.getMainLooper())
    }

    internal inner class AnalyticsReporterTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

        }
    }
}