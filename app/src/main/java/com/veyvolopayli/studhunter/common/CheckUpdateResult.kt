package com.veyvolopayli.studhunter.common

sealed class CheckUpdateResult<T>(val error: ErrorType? = null) {
    class UpdateAvailable<T>: CheckUpdateResult<T>()
    class LastVersionInstalled<T>: CheckUpdateResult<T>()
    class Error<T>(error: ErrorType?): CheckUpdateResult<T>(error)
}
