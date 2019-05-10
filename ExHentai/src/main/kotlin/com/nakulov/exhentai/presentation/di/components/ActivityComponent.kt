package com.nakulov.exhentai.presentation.di.components

import androidx.appcompat.app.AppCompatActivity
import com.nakulov.exhentai.presentation.activity.LaunchActivity
import com.nakulov.exhentai.presentation.di.PerActivity
import com.nakulov.exhentai.presentation.di.modules.ActivityModule
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun activity(): AppCompatActivity

    fun inject(launchActivity: LaunchActivity)

}
