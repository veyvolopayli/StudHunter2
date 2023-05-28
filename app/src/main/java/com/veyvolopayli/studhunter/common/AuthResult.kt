package com.veyvolopayli.studhunter.common

sealed class AuthResult<T>(val errorType: ErrorType? = null) {
    class Authorized<T>: AuthResult<T>()
    class Unauthorized<T>: AuthResult<T>()
    class Error<T>(errorType: ErrorType?): AuthResult<T>(errorType)
//    class Loading<T>: AuthResult<T>()
}
