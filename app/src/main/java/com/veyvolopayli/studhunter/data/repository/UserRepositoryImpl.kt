package com.veyvolopayli.studhunter.data.repository

import com.veyvolopayli.studhunter.data.remote.StudHunterApi
import com.veyvolopayli.studhunter.data.remote.dto.MyPublicationDTO
import com.veyvolopayli.studhunter.data.remote.dto.PublicationDto
import com.veyvolopayli.studhunter.domain.model.Publication
import com.veyvolopayli.studhunter.domain.model.User
import com.veyvolopayli.studhunter.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: StudHunterApi
) : UserRepository {
    override suspend fun fetchUserById(token: String, id: String): User {
        return api.fetchUserById(token, id)
    }

    override suspend fun getCurrentUserId(token: String): String {
        return api.getCurrentUserId(token)
    }

    override suspend fun getUniversities(): List<String> {
        return api.getUniversities()
    }

    override suspend fun getUserPublications(userID: String): List<PublicationDto> {
        return api.getUserPublications(userID)
    }

    override suspend fun getMyPublications(token: String): List<MyPublicationDTO> {
        return api.getMyPublications(token)
    }
}