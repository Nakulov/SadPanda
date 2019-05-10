package com.nakulov.exhentai.presentation.di.modules

import android.content.Context
import android.content.res.Resources
import com.nakulov.exhentai.presentation.ExApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
open class ApplicationModule(private val application: ExApplication) {

    @Provides
    @Singleton
    open fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    open fun provideApplicationResource(): Resources = application.resources

}