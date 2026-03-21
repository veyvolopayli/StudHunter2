package com.veyvolopayli.studhunter.common

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File

object FileUtil {
    fun uriFromFile(context: Context, file: File): Uri? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(
                context,
                Constants.FILE_PROVIDER_AUTHORITY,
                file
            )
        } else {
            Uri.fromFile(file)
        }
    }
}