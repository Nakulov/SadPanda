package com.nakulov.exhentai.data.cache

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonParser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheSerializer @Inject constructor(
    private val gson: Gson
) {

    fun serialize(any: Any): String = gson.toJson(any)

    @Throws(JsonParseException::class)
    fun <T> deserialize(string: String, clazz: Class<T>): T {
        return gson.fromJson(string, clazz)
    }

    fun <T> deserializeCollection(string: String, clazz: Class<T>): List<T> {
        return try {
            val list = mutableListOf<T>()
            val array = JsonParser().parse(string).asJsonArray
            for (element in array) {
                list.add(gson.fromJson<T>(element, clazz))
            }
            list
        } catch (fne: JsonParseException) {
            mutableListOf()
        }
    }

}