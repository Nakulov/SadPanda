package com.nakulov.exhentai.presentation.di.components

import android.content.Context
import com.nakulov.exhentai.data.cache.ExCache
import com.nakulov.exhentai.presentation.di.modules.ApplicationModule
import com.nakulov.exhentai.presentation.di.modules.net.GsonModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, GsonModule::class])
interface ApplicationComponent {

    fun context(): Context

    fun exCache(): ExCache

}