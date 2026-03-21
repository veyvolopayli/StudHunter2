package com.veyvolopayli.studhunter.domain.model.responses

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String
)
