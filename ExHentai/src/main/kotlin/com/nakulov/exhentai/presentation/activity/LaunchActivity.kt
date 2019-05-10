package com.nakulov.exhentai.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nakulov.exhentai.R
import com.nakulov.exhentai.presentation.ExApplication.Companion.applicationComponent
import com.nakulov.exhentai.presentation.di.HasComponent
import com.nakulov.exhentai.presentation.di.components.ActivityComponent
import com.nakulov.exhentai.presentation.di.components.DaggerActivityComponent
import com.nakulov.exhentai.presentation.di.modules.ActivityModule

class LaunchActivity : AppCompatActivity(), HasComponent<ActivityComponent> {

    private lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjectors()
        activityComponent.inject(this)
        setContentView(R.layout.activity_launch)
    }

    private fun initInjectors() {
        activityComponent = DaggerActivityComponent.builder()
            .applicationComponent(applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
    }

    override fun getComponent(): ActivityComponent = activityComponent
}
