package com.veyvolopayli.studhunter.data.remote

import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.model.responses.SignInResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface StudHunterApi {

    @GET("publications/fetch")
    suspend fun fetchPublications(): List<PublicationDto>

    @POST("signin")
    suspend fun signIn(@Body signInRequest: SignInRequest): SignInResponse

    @POST("signun")
    suspend fun signUp(@Body signUpRequest: SignUpRequest)

    @POST("authenticate")
    suspend fun authenticate(@Header("Authorization") token: String)

}