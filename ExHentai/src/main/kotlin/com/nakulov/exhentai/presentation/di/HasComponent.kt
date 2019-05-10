package com.nakulov.exhentai.presentation.di

interface HasComponent<out C> {

    fun getComponent(): C

}