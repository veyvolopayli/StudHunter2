package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.domain.model.MyPublication
import com.veyvolopayli.studhunter.domain.model.Publication
import com.veyvolopayli.studhunter.domain.model.University
import com.veyvolopayli.studhunter.domain.model.User
import com.veyvolopayli.studhunter.domain.model.requests.EditProfileRequest
import com.veyvolopayli.studhunter.domain.model.WideTask
import okhttp3.MultipartBody

interface UserRepository {
    suspend fun fetchUserById(token: String, id: String): User
    suspend fun getCurrentUserId(token: String): String
    suspend fun getUniversities(): List<University>
    suspend fun getUserPublications(userID: String): List<Publication>
    suspend fun getMyPublications(token: String): List<MyPublication>
    suspend fun editProfile(token: String, editProfileRequest: EditProfileRequest): Boolean
    suspend fun uploadProfileImage(token: String, image: MultipartBody.Part): String
    suspend fun getTasks(token: String, userId: String, userStatus: String, taskStatus: String): List<WideTask>
}