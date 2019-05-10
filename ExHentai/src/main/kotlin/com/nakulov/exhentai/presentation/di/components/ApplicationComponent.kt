package com.nakulov.exhentai.presentation.di.components

import android.content.Context
import com.nakulov.exhentai.presentation.di.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun context(): Context

}