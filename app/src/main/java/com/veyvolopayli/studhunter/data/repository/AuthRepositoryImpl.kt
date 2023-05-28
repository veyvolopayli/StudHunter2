package com.veyvolopayli.studhunter.data.repository

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.data.remote.StudHunterApi
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.model.responses.AuthResponse
import com.veyvolopayli.studhunter.domain.repository.AuthRepository
import retrofit2.HttpException

class AuthRepositoryImpl(private val api: StudHunterApi, private val prefs: SharedPreferences) : AuthRepository {
    override suspend fun signUp(signUpRequest: SignUpRequest): AuthResponse {
        return api.signUp(signUpRequest)
    }

    override suspend fun signIn(signInRequest: SignInRequest): AuthResponse {
        return api.signIn(signInRequest)
    }

    override suspend fun authenticate(token: String) {
//        val token = prefs.getString("jwt", null) ?: return
        api.authenticate(token)
    }

}
