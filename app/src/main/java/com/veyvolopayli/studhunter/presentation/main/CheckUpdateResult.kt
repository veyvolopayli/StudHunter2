package com.veyvolopayli.studhunter.presentation.main

import com.veyvolopayli.studhunter.common.ErrorType

sealed class CheckUpdateResult<T>(val error: ErrorType? = null) {
    class UpdateAvailable<T>: CheckUpdateResult<T>()
    class LastVersionInstalled<T>: CheckUpdateResult<T>()
    class Error<T>(error: ErrorType?): CheckUpdateResult<T>(error)
}
