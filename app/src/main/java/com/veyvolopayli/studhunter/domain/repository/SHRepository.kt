package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto

interface SHRepository {

    suspend fun fetchPublications(): List<PublicationDto>

}