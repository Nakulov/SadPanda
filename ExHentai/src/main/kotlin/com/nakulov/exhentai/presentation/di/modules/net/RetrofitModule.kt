package com.nakulov.exhentai.presentation.di.modules.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nakulov.exhentai.data.net.EmptyBodyConverterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
@Module(includes = [OkHttpClientModule::class, GsonModule::class])
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(
            okHttpClient: OkHttpClient,
            gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(EmptyBodyConverterFactory())
            .addConverterFactory(gsonConverterFactory)
            .build()

}