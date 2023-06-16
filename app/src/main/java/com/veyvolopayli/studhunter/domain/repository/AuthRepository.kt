package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.model.responses.AuthResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun signUp(signUpRequest: SignUpRequest): Response<AuthResponse>
    suspend fun signIn(signInRequest: SignInRequest): AuthResponse
    suspend fun authenticate(token: String)
    suspend fun isUsernameUnique(username: String)
    suspend fun isEmailUnique(email: String)
}