package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import com.veyvolopayli.studhunter.domain.model.Publication
import com.veyvolopayli.studhunter.domain.model.PublicationToUpload
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Part

interface PublicationRepository {

    suspend fun fetchPublications(): List<PublicationDto>

    suspend fun fetchPublication(token: String, id: String): Response<PublicationDto>

    suspend fun checkImageValidity(publicationId: String, num: Int)

    suspend fun getCategories(): Map<Int, String>

    suspend fun uploadPublication(
        imageFiles: List<MultipartBody.Part>,
        publicationData: PublicationToUpload,
        token: String
    ): String

    suspend fun getPriceTypes(): Map<Int, String>

    suspend fun getDistricts(): List<String>
}