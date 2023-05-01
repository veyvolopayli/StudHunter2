package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import retrofit2.http.Path

interface PublicationRepository {

    suspend fun getPublications(): List<PublicationDto>

    suspend fun getPublicationById(publicationId: String): PublicationDto

    suspend fun getPublicationsByCategory(category: String): List<PublicationDto>

    suspend fun getPublicationsByQuery(query: String): List<PublicationDto>

}