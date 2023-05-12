package com.veyvolopayli.studhunter.common

sealed class AuthResult<T>(val data: T? = null) {
    class Authorized<T>(data: T? = null): AuthResult<T>(data)
    class Unauthorized<T>: AuthResult<T>()
    class UnknownError<T>: AuthResult<T>()
    class WrongPassword<T>: AuthResult<T>()
    class Loading<T>: AuthResult<T>()
}
