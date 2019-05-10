package com.nakulov.exhentai.presentation.di.components

import com.nakulov.exhentai.presentation.di.PerFragment
import com.nakulov.exhentai.presentation.di.modules.FragmentModule
import dagger.Component

@PerFragment
@Component(dependencies = [(ApplicationComponent::class)], modules = [(FragmentModule::class)])
interface FragmentComponent