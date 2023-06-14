package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import com.veyvolopayli.studhunter.domain.model.Publication
import retrofit2.Response

interface PublicationRepository {

    suspend fun fetchPublications(): List<PublicationDto>

    suspend fun fetchPublication(token: String, id: String): Response<PublicationDto>

    suspend fun checkImageValidity(publicationId: String, num: Int)

    suspend fun getCategories(): Map<Int, String>

}