package com.veyvolopayli.studhunter.data.repository

import com.veyvolopayli.studhunter.data.remote.StudHunterApi
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.model.responses.SignInResponse
import com.veyvolopayli.studhunter.domain.repository.AuthRepository
import retrofit2.http.Body
import retrofit2.http.POST

class AuthRepositoryImpl(private val api: StudHunterApi) : AuthRepository {
    override suspend fun signUp(signUpRequest: SignUpRequest) {
        api.signUp(signUpRequest)
    }

    override suspend fun signIn(signInRequest: SignInRequest): SignInResponse {
        return api.signIn(signInRequest)
    }

}
