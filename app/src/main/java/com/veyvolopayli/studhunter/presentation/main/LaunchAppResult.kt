package com.veyvolopayli.studhunter.presentation.main

import com.veyvolopayli.studhunter.common.ErrorType

sealed class LaunchAppResult<T>(val error: ErrorType? = null) {
    class NeedToAuthorize<T>: LaunchAppResult<T>()
    class NeedToUpdate<T>: LaunchAppResult<T>()
    class Ok<T>: LaunchAppResult<T>()
    class ErrorOccurred<T>(error: ErrorType?): LaunchAppResult<T>(error)
}
