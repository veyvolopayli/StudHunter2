package com.veyvolopayli.studhunter.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class University(
    val id: String,
    val name: String,
    @SerialName("shortname")
    val shortName: String
)
