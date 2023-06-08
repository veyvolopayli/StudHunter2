package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import com.veyvolopayli.studhunter.domain.model.Publication
import retrofit2.Response

interface PublicationRepository {

    suspend fun fetchPublications(): List<PublicationDto>

    suspend fun fetchPublication(id: String): Response<PublicationDto>

}