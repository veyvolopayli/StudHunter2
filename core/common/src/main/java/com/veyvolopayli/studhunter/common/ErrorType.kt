package com.veyvolopayli.studhunter.common

sealed class ErrorType(val message: String = "") {
    class LocalError(message: String = "") : ErrorType(message)
    class ServerError(message: String = "") : ErrorType(message)
    class NetworkError(message: String = "") : ErrorType(message)
    class Unauthorized() : ErrorType()
}
