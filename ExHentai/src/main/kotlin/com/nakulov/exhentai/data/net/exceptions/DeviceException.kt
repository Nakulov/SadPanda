package com.nakulov.exhentai.data.net.exceptions

import java.io.IOException

open class DeviceException : IOException {
    constructor(message: String) : super(message)
    constructor(throwable: Throwable) : super(throwable)
}

class NoInternetException : DeviceException {
    constructor(message: String) : super(message)
    constructor(throwable: Throwable) : super(throwable)
}

class NoUserTokenException(message: String) : DeviceException(message)
class EmptyDataException(message: String) : DeviceException(message)