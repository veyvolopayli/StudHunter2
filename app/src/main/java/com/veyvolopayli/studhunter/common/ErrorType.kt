package com.veyvolopayli.studhunter.common

sealed class ErrorType {
    object NetworkError : ErrorType()
    object ServerError : ErrorType()
    object UnexpectedError : ErrorType()
}
