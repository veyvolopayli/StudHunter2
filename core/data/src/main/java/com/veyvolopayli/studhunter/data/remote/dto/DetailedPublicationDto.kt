package com.veyvolopayli.studhunter.data.remote.dto

import com.veyvolopayli.studhunter.domain.model.DetailedPublication
import com.veyvolopayli.studhunter.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
data class DetailedPublicationDto(
    val publication: PublicationDto,
    val user: User,
    val userIsOwner: Boolean,
)

fun DetailedPublicationDto.toDetailedPublication(): DetailedPublication =
    DetailedPublication(
        publication = publication.toPublication(),
        user = user,
        userIsOwner = userIsOwner,
    )
