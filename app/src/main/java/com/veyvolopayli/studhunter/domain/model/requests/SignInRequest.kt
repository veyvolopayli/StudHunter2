package com.veyvolopayli.studhunter.domain.model.requests

@kotlinx.serialization.Serializable
data class SignInRequest(
    val username: String,
    val password: String
)
