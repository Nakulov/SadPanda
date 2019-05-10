package com.nakulov.exhentai.presentation.di

interface HasComponent<out C> {

    val component: C

}