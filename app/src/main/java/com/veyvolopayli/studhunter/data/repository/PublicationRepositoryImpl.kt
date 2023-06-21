package com.veyvolopayli.studhunter.data.repository

import com.veyvolopayli.studhunter.data.remote.StudHunterApi
import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import com.veyvolopayli.studhunter.domain.model.PublicationToUpload
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import okhttp3.MultipartBody
import retrofit2.Response

class PublicationRepositoryImpl(private val api: StudHunterApi): PublicationRepository {

    override suspend fun fetchPublications(): List<PublicationDto> {
        return api.fetchPublications()
    }

    override suspend fun fetchPublication(token: String, id: String): Response<PublicationDto> {
        return api.fetchPublication(token, id)
    }

    override suspend fun checkImageValidity(publicationId: String, num: Int) {
        api.checkImageValidity(publicationId, num)
    }

    override suspend fun getCategories(): Map<Int, String> {
        return api.getCategories()
    }

    override suspend fun uploadPublication(
        imageFiles: List<MultipartBody.Part>,
        publicationData: PublicationToUpload,
        token: String
    ): String {
        return api.uploadPublication(imageFiles, publicationData, token)
    }

}