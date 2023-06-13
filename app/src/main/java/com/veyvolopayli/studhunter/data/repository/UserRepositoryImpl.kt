package com.veyvolopayli.studhunter.data.repository

import com.veyvolopayli.studhunter.data.remote.StudHunterApi
import com.veyvolopayli.studhunter.domain.model.User
import com.veyvolopayli.studhunter.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: StudHunterApi
) : UserRepository {
    override suspend fun fetchUserById(token: String, id: String): User {
        return api.fetchUserById(token, id)
    }
}