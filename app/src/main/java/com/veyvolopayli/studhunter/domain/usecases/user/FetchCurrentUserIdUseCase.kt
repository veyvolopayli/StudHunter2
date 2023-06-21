package com.veyvolopayli.studhunter.domain.usecases.user

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class FetchCurrentUserIdUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val prefs: SharedPreferences
) {
    operator fun invoke(): Flow<String?> = flow {
        try {
            val token = prefs.getString("jwt", null) ?: run {
                emit(null)
                return@flow
            }
            val userId = userRepository.getCurrentUserId(token)
            emit(userId)
        } catch (e: Exception) {
            emit(null)
        }
    }
}