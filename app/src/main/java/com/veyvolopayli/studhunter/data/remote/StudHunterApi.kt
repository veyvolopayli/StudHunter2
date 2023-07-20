package com.veyvolopayli.studhunter.data.remote

import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import com.veyvolopayli.studhunter.domain.model.PublicationToUpload
import com.veyvolopayli.studhunter.domain.model.User
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.model.responses.AuthResponse
import com.veyvolopayli.studhunter.domain.model.responses.CheckUpdateResponse
import okhttp3.MultipartBody
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

    @GET("user/public/get")
    suspend fun isUsernameUnique(@Query("username") username: String)

    @GET("user/public/get")
    suspend fun isEmailUnique(@Query("email") email:String)

    @GET("publications/id/{id}")
    suspend fun fetchPublication(@Header("Authorization") token: String, @Path("id") id: String): Response<PublicationDto>

    @GET("image/{publicationId}/image_{n}")
    suspend fun checkImageValidity(@Path("publicationId") publicationId: String, @Path("n") n: Int)

    @GET("user/get")
    suspend fun fetchUserById(@Header("Authorization") token: String, @Query("id") id: String): User

    @GET("publication/categories")
    suspend fun getCategories(): Map<Int, String>

    @GET("userid")
    suspend fun getCurrentUserId(@Header("Authorization") token: String): String

    @Multipart
    @POST("publications/new")
    suspend fun uploadPublication(
        @Part imageFiles: List<MultipartBody.Part>,
        @Part("publicationData") publicationData: PublicationToUpload,
        @Header("Authorization") token: String
    ): String

    @GET("publication/priceTypes")
    suspend fun getPriceTypes(): Map<Int, String>

    @GET("publication/districts")
    suspend fun getDistricts(): List<String>

    @GET("universities/get")
    suspend fun getUniversities(): List<String>
}