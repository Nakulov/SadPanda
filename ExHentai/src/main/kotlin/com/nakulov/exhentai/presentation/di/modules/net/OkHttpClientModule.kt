package com.nakulov.exhentai.presentation.di.modules.net

import com.nakulov.exhentai.data.DeviceUtils
import com.nakulov.exhentai.data.net.AuthInterceptor
import com.nakulov.exhentai.data.net.NoInternetInterceptor
import com.nakulov.exhentai.data.net.RestExceptionInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import java.util.concurrent.TimeUnit.MILLISECONDS

@Module
open class OkHttpClientModule {

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        restExceptionInterceptor: RestExceptionInterceptor,
        notInternetInterceptor: NoInternetInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(notInternetInterceptor)
            .addInterceptor(authInterceptor)
            .addInterceptor(restExceptionInterceptor)
            .readTimeout(10000, MILLISECONDS)
            .connectTimeout(15000, MILLISECONDS)
            .build()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(BODY)

    @Provides
    fun provideAuthInterceptor() = AuthInterceptor()

    @Provides
    fun provideRestExceptionInterceptor() = RestExceptionInterceptor()

    @Provides
    fun provideNoInternetInterceptor(deviceUtils: DeviceUtils) = NoInternetInterceptor(deviceUtils)

}