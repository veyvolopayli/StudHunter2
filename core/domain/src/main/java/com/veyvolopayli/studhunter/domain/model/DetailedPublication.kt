package com.veyvolopayli.studhunter.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DetailedPublication(
    val publication: Publication,
    val user: User,
    val userIsOwner: Boolean,
)