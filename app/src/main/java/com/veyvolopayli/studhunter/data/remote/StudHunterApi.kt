package com.veyvolopayli.studhunter.data.remote

import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import com.veyvolopayli.studhunter.domain.model.User
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.model.responses.AuthResponse
import com.veyvolopayli.studhunter.domain.model.responses.CheckUpdateResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface StudHunterApi {

    @GET("publications/fetch")
    suspend fun fetchPublications(): List<PublicationDto>

    @POST("signin")
    suspend fun signIn(@Body signInRequest: SignInRequest): AuthResponse

    @POST("signup")
    suspend fun signUp(@Body signUpRequest: SignUpRequest) : Response<AuthResponse>

    @GET("authenticate")
    suspend fun authenticate(@Header("Authorization") token: String)

    @GET("update/check/{version}")
    suspend fun checkUpdate(@Path("version") version: String): CheckUpdateResponse

    @GET("update/download/last")
    @Streaming
    suspend fun downloadUpdate(): ResponseBody

    @GET("user/")
    suspend fun isUsernameUnique(username: String): Boolean

    @GET("")
    suspend fun isEmailUnique(email:String): Boolean

    @GET("publications/id/{id}")
    suspend fun fetchPublication(@Header("Authorization") token: String, @Path("id") id: String): Response<PublicationDto>

    @GET("image/{publicationId}/image_{n}")
    suspend fun checkImageValidity(@Path("publicationId") publicationId: String, @Path("n") n: Int)

    @GET("users/{id}")
    suspend fun fetchUserById(@Header("Authorization") token: String, @Path("id") id: String): User

}