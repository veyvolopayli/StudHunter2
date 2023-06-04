package com.veyvolopayli.studhunter.presentation.update_app_screen

sealed class InstallUpdateResult<T>(val message: String = "") {
    class Error<T>(message: String): InstallUpdateResult<T>(message)
    class FileNotFound<T>: InstallUpdateResult<T>()
}
