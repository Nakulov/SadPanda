package com.nakulov.exhentai.data.net

import com.google.gson.JsonSyntaxException
import com.nakulov.exhentai.data.net.exceptions.ClientException
import com.nakulov.exhentai.data.net.exceptions.RestExceptions
import com.nakulov.exhentai.data.net.exceptions.ServerException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

typealias SuccessHandler<T> = (T) -> Unit
typealias ErrorHandler = (Throwable) -> Unit

sealed class ResponseType<T> {
    data class Error<T>(val throwable: Throwable) : ResponseType<T>()
    data class Response<T>(val response: T) : ResponseType<T>()
}

@Suppress("EXPERIMENTAL_API_USAGE")
fun <T> CoroutineScope.runSingleRequest(
    success: SuccessHandler<T>,
    error: ErrorHandler,
    requestBody: () -> Deferred<T>
) = launch {

    val sender = actor<ResponseType<T>>(Dispatchers.Main) {
        for (message in channel) {
            when (message) {
                is ResponseType.Error -> {
                    Timber.e(message.throwable, "response end with fail cause")
                    error(message.throwable)
                }
                is ResponseType.Response -> success(message.response)
            }
        }
    }

    val newRequest = requestBody()

    try {
        sender.send(ResponseType.Response(newRequest.await()))
    } catch (he: HttpException) {
        sender.send(ResponseType.Error(RestExceptions(he)))
    } catch (jse: JsonSyntaxException) {
        sender.send(ResponseType.Error(RestExceptions(jse)))
    } catch (ce: ClientException) {
        sender.send(ResponseType.Error(ClientException(ce)))
    } catch (se: ServerException) {
        sender.send(ResponseType.Error(ServerException(se)))
    } catch (e: Exception) {
        sender.send(ResponseType.Error(RestExceptions(e)))
    }
}