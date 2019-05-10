package com.nakulov.exhentai.presentation.di.modules

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nakulov.exhentai.presentation.di.PerFragment
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@PerFragment
class FragmentModule(private val fragment: Fragment) {

    @Provides
    @PerFragment
    fun provideActivity(): AppCompatActivity = fragment.activity as AppCompatActivity

}