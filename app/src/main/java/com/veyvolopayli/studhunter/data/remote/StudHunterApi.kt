package com.veyvolopayli.studhunter.data.remote

import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import retrofit2.http.GET
import retrofit2.http.Path

interface StudHunterApi {

    @GET("publications/fetch")
    suspend fun getPublications(): List<PublicationDto>

    @GET("publications/id/{id}")
    suspend fun getPublicationById(@Path("id") publicationId: String): PublicationDto

    @GET("publications/category/{category}")
    suspend fun getPublicationsByCategory(@Path("category") category: String): List<PublicationDto>

    @GET("publications/query/{query}")
    suspend fun getPublicationsByQuery(@Path("query") query: String): List<PublicationDto>

}