package com.nakulov.exhentai.presentation.di.components

import com.nakulov.exhentai.presentation.di.PerFragment
import com.nakulov.exhentai.presentation.di.modules.FragmentModule
import com.nakulov.exhentai.presentation.fragment.BaseFragment
import dagger.Component

@PerFragment
@Component(dependencies = [(ApplicationComponent::class)], modules = [(FragmentModule::class)])
interface FragmentComponent {

    fun inject(baseFragment: BaseFragment)

}