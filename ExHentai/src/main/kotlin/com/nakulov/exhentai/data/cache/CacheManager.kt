package com.nakulov.exhentai.data.cache

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheManager @Inject constructor(
    private val context: Context
) {

    fun <T> writeToPreference(preferencesFileName: String, key: String, value: T) = with(
        context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE).edit()) {
        when (value) {
            is Long -> putLong(key, value)
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            else -> throw IllegalArgumentException("This type can't be saved into Preferences")
        }.apply()
    }

    @Suppress("UNCHECKED_CAST")
    fun <U> readFromPreference(preferencesFileName: String, key: String, default: U): U = with(
        context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE)) {
        val res: Any = when (default) {
            is Long -> getLong(key, default)
            is String -> getString(key, default)
            is Int -> getInt(key, default)
            is Boolean -> getBoolean(key, default)
            is Float -> getFloat(key, default)
            else -> throw  IllegalArgumentException("This type can't get from Preferences")
        }

        res as U
    }

}