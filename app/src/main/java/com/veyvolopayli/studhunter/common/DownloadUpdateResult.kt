package com.veyvolopayli.studhunter.common

import java.io.File

sealed class DownloadUpdateResult<T>(val downloadedUpdate: File? = null) {
    class Downloading<T>: DownloadUpdateResult<T>()
    class Downloaded<T>(downloadedUpdate: File? = null): DownloadUpdateResult<T>(downloadedUpdate)
    class Error<T>: DownloadUpdateResult<T>()
}
