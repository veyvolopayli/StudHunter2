package com.veyvolopayli.studhunter.presentation.main

import com.veyvolopayli.studhunter.common.ErrorType

sealed class LaunchAppResult(val error: ErrorType? = null) {
    class Success: LaunchAppResult()
    class NotAuthorized: LaunchAppResult()
    class UpdateAvailable: LaunchAppResult()
    class Error(error: ErrorType?): LaunchAppResult(error)
}
