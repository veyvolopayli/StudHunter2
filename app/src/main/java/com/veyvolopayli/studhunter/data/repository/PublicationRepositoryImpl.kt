package com.veyvolopayli.studhunter.data.repository

import com.veyvolopayli.studhunter.data.remote.StudHunterApi
import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import retrofit2.Response

class PublicationRepositoryImpl(private val api: StudHunterApi): PublicationRepository {

    override suspend fun fetchPublications(): List<PublicationDto> {
        return api.fetchPublications()
    }

    override suspend fun fetchPublication(token: String, id: String): Response<PublicationDto> {
        return api.fetchPublication(token, id)
    }

}