package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.data.remote.dto.MyPublicationDTO
import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import com.veyvolopayli.studhunter.domain.model.Publication
import com.veyvolopayli.studhunter.domain.model.User
import com.veyvolopayli.studhunter.domain.model.requests.EditProfileRequest
import okhttp3.MultipartBody

interface UserRepository {
    suspend fun fetchUserById(token: String, id: String): User
    suspend fun getCurrentUserId(token: String): String
    suspend fun getUniversities(): List<String>
    suspend fun getUserPublications(userID: String): List<PublicationDto>
    suspend fun getMyPublications(token: String): List<MyPublicationDTO>
    suspend fun editProfile(token: String, editProfileRequest: EditProfileRequest): Boolean
    suspend fun uploadProfileImage(token: String, image: MultipartBody.Part): String
}