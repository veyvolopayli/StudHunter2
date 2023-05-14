package com.veyvolopayli.studhunter.common

sealed class CheckUpdateResult<T>(val data: T? = null) {
    class UpdateAvailable<T>(): CheckUpdateResult<T>()
    class LastVersionInstalled<T>: CheckUpdateResult<T>()
    class Error<T>: CheckUpdateResult<T>()
}
