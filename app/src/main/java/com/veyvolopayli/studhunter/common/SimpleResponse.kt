package com.veyvolopayli.studhunter.common

import kotlinx.serialization.Serializable

@Serializable
data class SimpleResponse<T>(
    val data: T
)
