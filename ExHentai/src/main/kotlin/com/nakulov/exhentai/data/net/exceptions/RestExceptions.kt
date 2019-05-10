package com.nakulov.exhentai.data.net.exceptions

import java.io.IOException

open class RestExceptions : IOException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(throwable: Throwable) : super(throwable)
}

/* 4XX: client error */
open class ClientException : RestExceptions {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(throwable: Throwable) : super(throwable)
}

class BadRequestException : ClientException() // 400
class UnauthorizedException : ClientException() // 401
class PaymentRequiredException : ClientException() // 402
class ForbiddenException : ClientException() // 403
class NotFoundException : ClientException() // 404
class BadMethodException : ClientException() // 405

/*HTTP Status-Code 500*/
open class ServerException : RestExceptions {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(throwable: Throwable) : super(throwable)
}

class InternalServerException : ServerException() // 500
class NotImplementedException : ServerException() // 501
class BadGatewayException : ServerException() // 502
class ServiceUnavailableException : ServerException() // 503
class GatewayTimeoutException : ServerException() // 504
class HTTPVersionNotSupportedException : ServerException() //505