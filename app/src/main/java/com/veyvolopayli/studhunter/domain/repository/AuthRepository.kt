package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.model.responses.AuthResponse

interface AuthRepository {
    suspend fun signUp(signUpRequest: SignUpRequest): AuthResponse
    suspend fun signIn(signInRequest: SignInRequest): AuthResponse
    suspend fun authenticate(token: String)
    suspend fun isUsernameUnique(username: String): Boolean
    suspend fun isEmailUnique(email: String): Boolean
}