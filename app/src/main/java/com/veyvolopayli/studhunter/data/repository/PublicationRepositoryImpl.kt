package com.veyvolopayli.studhunter.data.repository

import com.veyvolopayli.studhunter.data.remote.StudHunterApi
import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import javax.inject.Inject

class PublicationRepositoryImpl @Inject constructor(
    private val api: StudHunterApi
) : PublicationRepository {

    override suspend fun getPublications(): List<PublicationDto> {
        return api.getPublications()
    }

    override suspend fun getPublicationById(publicationId: String): PublicationDto {
        return api.getPublicationById(publicationId = publicationId)
    }

    override suspend fun getPublicationsByCategory(category: String): List<PublicationDto> {
        return api.getPublicationsByCategory(category = category)
    }

    override suspend fun getPublicationsByQuery(query: String): List<PublicationDto> {
        return api.getPublicationsByQuery(query = query)
    }

}