package com.nakulov.exhentai.data.net

import com.nakulov.exhentai.data.DeviceUtils
import com.nakulov.exhentai.data.net.exceptions.*
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.net.HttpURLConnection.*
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

private const val HEADER_NAME_CONTENT_TYPE = "ContentType"
private const val HEADER_VALUE_CONTENT_TYPE = "application/json"
private const val REGISTER_PATH = ""

class NoInternetInterceptor(private val deviceUtils: DeviceUtils) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!deviceUtils.isConnectedToInternet()) {
            throw NoInternetException("has no internet connection")
        }

        try {
            return chain.proceed(chain.request())
        } catch (ste: SocketTimeoutException) {
            throw NoInternetException(ste)
        } catch (un: UnknownHostException) {
            throw NoInternetException(un)
        } catch (se: SocketException) {
            throw NoInternetException(se)
        }
    }
}

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val builder = request
            .newBuilder()
            .addHeader(HEADER_NAME_CONTENT_TYPE, HEADER_VALUE_CONTENT_TYPE)

        val isRegister = request.url().url().path.contains(REGISTER_PATH)

        val token = ""

        if (!isRegister) {
            if (token.isEmpty()) {
                throw NoUserTokenException("user token has expired")
            } else {
                Timber.d("user token: $token")
            }
        }

        return chain.proceed(builder.build())
    }
}

class RestExceptionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val clientCode = 400..499
        val serverCode = 500..599

        val request = chain.request()

        val builder = request.newBuilder()

        val response = chain.proceed(builder.build())

        val responseCode = response.code()

        if (responseCode in clientCode) {
            throw when (responseCode) {
                HTTP_BAD_REQUEST -> BadRequestException()
                HTTP_UNAUTHORIZED -> UnauthorizedException()
                HTTP_PAYMENT_REQUIRED -> PaymentRequiredException()
                HTTP_FORBIDDEN -> ForbiddenException()
                HTTP_NOT_FOUND -> NotFoundException()
                HTTP_BAD_METHOD -> BadMethodException()
                else -> ClientException("Undefined client exception with code: $responseCode")
            }
        } else if (responseCode in serverCode) {
            throw when (responseCode) {
                HTTP_INTERNAL_ERROR -> InternalServerException()
                HTTP_NOT_IMPLEMENTED -> NotImplementedException()
                HTTP_BAD_GATEWAY -> BadGatewayException()
                HTTP_UNAVAILABLE -> ServiceUnavailableException()
                HTTP_GATEWAY_TIMEOUT -> GatewayTimeoutException()
                HTTP_VERSION -> HTTPVersionNotSupportedException()
                else -> ServerException("Undefined server exception with code: $responseCode")
            }
        }

        return response
    }
}