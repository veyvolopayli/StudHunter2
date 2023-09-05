package com.veyvolopayli.studhunter.domain.model

import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import kotlinx.serialization.Serializable

@Serializable
data class DetailedPublication(
    val publication: PublicationDto,
    val user: User,
    val userIsOwner: Boolean
)