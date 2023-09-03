package com.veyvolopayli.studhunter.domain.model.chat

import kotlinx.serialization.Serializable

@Serializable
data class IncomingTextFrame(
    val type: String,
    val jsonStringObj: String
)
