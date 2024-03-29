package com.veyvolopayli.studhunter.data.remote

import com.veyvolopayli.studhunter.data.remote.dto.Chat
import com.veyvolopayli.studhunter.data.remote.dto.MessageDTO
import com.veyvolopayli.studhunter.data.remote.dto.MyPublicationDTO
import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import com.veyvolopayli.studhunter.domain.model.DetailedChat
import com.veyvolopayli.studhunter.domain.model.review.NewReviewRequest
import com.veyvolopayli.studhunter.domain.model.DetailedPublication
import com.veyvolopayli.studhunter.domain.model.FilterRequest
import com.veyvolopayli.studhunter.domain.model.PublicationToUpload
import com.veyvolopayli.studhunter.domain.model.User
import com.veyvolopayli.studhunter.domain.model.chat.Task
import com.veyvolopayli.studhunter.domain.model.requests.ChangePubFavoriteStatusRequest
import com.veyvolopayli.studhunter.domain.model.requests.EditProfileRequest
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.model.responses.AuthResponse
import com.veyvolopayli.studhunter.domain.model.responses.CheckUpdateResponse
import com.veyvolopayli.studhunter.domain.model.WideTask
import com.veyvolopayli.studhunter.domain.model.review.ReviewDto
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
    suspend fun signUp(@Body signUpRequest: SignUpRequest): Response<AuthResponse>

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
    suspend fun isEmailUnique(@Query("email") email: String)

    @GET("publications/id/{id}")
    suspend fun fetchPublication(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): DetailedPublication

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

    @GET("chats/get")
    suspend fun getChats(@Header("Authorization") token: String): List<DetailedChat>

    @GET("favorites/publication/{id}/check")
    suspend fun checkPubFavoriteStatus(
        @Header("Authorization") token: String,
        @Path("id") pubID: String
    ): Boolean

    @POST("favorites/publication/add")
    suspend fun addPubToFavorite(
        @Header("Authorization") token: String,
        @Body changePubFavoriteStatusRequest: ChangePubFavoriteStatusRequest
    ): Boolean

    @POST("favorites/publication/remove-single")
    suspend fun removePubFromFavorite(
        @Header("Authorization") token: String,
        @Body changePubFavoriteStatusRequest: ChangePubFavoriteStatusRequest
    ): Boolean

    @GET("user/{id}/publications")
    suspend fun getUserPublications(@Path("id") userID: String): List<PublicationDto>

    @GET("my-publications")
    suspend fun getMyPublications(@Header("Authorization") token: String): List<MyPublicationDTO>

    @Multipart
    @POST("avatar/upload")
    suspend fun uploadAvatar(
        @Header("Authorization") token: String,
        @Part avatar: MultipartBody.Part
    ): String

    @POST("profile/edit")
    suspend fun editProfile(
        @Header("Authorization") token: String,
        @Body editProfileRequest: EditProfileRequest
    ): Boolean

    @GET("publications/query/{query}")
    suspend fun searchPublications(@Path("query") query: String): List<PublicationDto>

    @GET("chat/by-chat_id/{chatID}/messages")
    suspend fun getMessagesByChatId(
        @Header("Authorization") token: String,
        @Path("chatID") chatId: String
    ): List<MessageDTO>

    @GET("chat/by-publication_id/{pubID}/messages")
    suspend fun getMessagesByPublicationId(
        @Header("Authorization") token: String,
        @Path("pubID") pubId: String
    ): List<MessageDTO>

    @POST("publications/filtered")
    suspend fun getFilteredPublications(@Body filterRequest: FilterRequest): List<PublicationDto>

    @GET("chat/task")
    suspend fun getTaskByChatId(
        @Header("Authorization") token: String,
        @Query("chatId") chatId: String
    ): Task

    @GET("chat/task")
    suspend fun getTaskByPubId(
        @Header("Authorization") token: String,
        @Query("pubId") pubId: String
    ): Task

    @GET("tasks")
    suspend fun getTasks(
        @Header("Authorization") token: String,
        @Query("userId") userId: String,
        @Query("userStatus") userStatus: String,
        @Query("taskStatus") taskStatus: String
    ): List<WideTask>

    @POST("reviews/new")
    suspend fun uploadReview(@Header("Authorization") token: String, @Body newReviewRequest: NewReviewRequest): ReviewDto
}