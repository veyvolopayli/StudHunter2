package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto

interface PublicationRepository {

    suspend fun fetchPublications(): List<PublicationDto>

}