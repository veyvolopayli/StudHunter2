package com.veyvolopayli.studhunter.presentation.update_app_screen

import java.io.File

sealed class DownloadUpdateResult<T>(val downloadedUpdate: File? = null, val message: String = "") {
    class Downloading<T>: DownloadUpdateResult<T>()
    class Downloaded<T>(downloadedUpdate: File? = null): DownloadUpdateResult<T>(downloadedUpdate)
    class Error<T>(message: String): DownloadUpdateResult<T>(message = message)
}
