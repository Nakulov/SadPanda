package com.nakulov.exhentai.data

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceUtils @Inject constructor(private val context: Context) {

    fun isConnectedToInternet(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val activeNetwork = connectivityManager?.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }

}