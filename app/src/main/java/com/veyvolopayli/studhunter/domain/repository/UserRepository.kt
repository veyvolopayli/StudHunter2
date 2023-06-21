package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.domain.model.User

interface UserRepository {
    suspend fun fetchUserById(token: String, id: String): User
    suspend fun getCurrentUserId(token: String): String
}