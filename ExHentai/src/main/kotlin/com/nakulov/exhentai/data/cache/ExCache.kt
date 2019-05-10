package com.nakulov.exhentai.data.cache

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExCache @Inject constructor(
    private var cacheManager: CacheManager,
    private var serializer: CacheSerializer
) {

    companion object {
        //Configs
        private const val APPLICATION_CONFIG = "com.nakulov.exhentai.CONFIG"
        private const val USER_CONFIG = "com.nakulov.exhentai.USER_CONFIG"

        //App states
        private const val APPLICATION_STATE = "appState"

        private const val IS_FIRST_LAUNCH = "isFirstLaunch"
        private const val IS_LOGGED_IN = "isLoggedIn"
    }


    var isFirstLaunch: Boolean
        set(value) = cacheManager.writeToPreference(APPLICATION_CONFIG, IS_FIRST_LAUNCH, value)
        get() = cacheManager.readFromPreference(APPLICATION_CONFIG, IS_FIRST_LAUNCH, true)

    var isLoggedIn: Boolean
        set(value) = cacheManager.writeToPreference(USER_CONFIG, IS_LOGGED_IN, value)
        get() = cacheManager.readFromPreference(USER_CONFIG, IS_LOGGED_IN, false)

}

