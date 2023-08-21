package com.veyvolopayli.studhunter.domain.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class EditProfileRequest(
    val name: String,
    val surname: String,
    val university: String
)
