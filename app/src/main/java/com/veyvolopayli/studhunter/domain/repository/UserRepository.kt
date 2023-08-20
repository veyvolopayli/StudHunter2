package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.data.remote.dto.MyPublicationDTO
import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import com.veyvolopayli.studhunter.domain.model.Publication
import com.veyvolopayli.studhunter.domain.model.User

interface UserRepository {
    suspend fun fetchUserById(token: String, id: String): User
    suspend fun getCurrentUserId(token: String): String
    suspend fun getUniversities(): List<String>
    suspend fun getUserPublications(userID: String): List<PublicationDto>
    suspend fun getMyPublications(token: String): List<MyPublicationDTO>
}