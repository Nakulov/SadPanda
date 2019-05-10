package com.nakulov.exhentai.presentation.di.modules

import androidx.appcompat.app.AppCompatActivity
import com.nakulov.exhentai.presentation.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
@PerActivity
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @PerActivity
    fun activity(): AppCompatActivity = activity

}