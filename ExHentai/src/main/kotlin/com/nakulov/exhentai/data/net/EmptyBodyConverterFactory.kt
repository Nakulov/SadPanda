package com.nakulov.exhentai.data.net

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class EmptyBodyConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        val delegate = retrofit.nextResponseBodyConverter<ResponseBody>(this, type, annotations)

        return Converter<ResponseBody, Any> { body ->
            return@Converter if (body.contentLength() == 0L) EmptyObject() else delegate.convert(body)
        }
    }

}

class EmptyObject