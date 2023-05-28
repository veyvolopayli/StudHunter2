package com.veyvolopayli.studhunter.common

sealed class AuthorizationResult<T>(val data: T? = null, val error: ErrorType? = null) {
    class Authorized<T>(data: T? = null): AuthorizationResult<T>(data = data)
    class WrongData<T>: AuthorizationResult<T>()
    class UnknownError<T>: AuthorizationResult<T>()
    class Error<T>(error: ErrorType?): AuthorizationResult<T>(error = error)
}