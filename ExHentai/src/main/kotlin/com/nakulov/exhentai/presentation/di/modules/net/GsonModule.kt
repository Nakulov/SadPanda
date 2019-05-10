package com.nakulov.exhentai.presentation.di.modules.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory

@Module
class GsonModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder()
            .create()

    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

}