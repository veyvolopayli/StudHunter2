package com.veyvolopayli.studhunter.presentation.update_app_screen

import java.io.File

data class UpdateAppState(
    val downloading: Boolean = false,
    val downloaded: Boolean = false,
    val buttonPressed: Boolean = false,
    val error: String = "",
    val updateAppFile: File? = null
)
