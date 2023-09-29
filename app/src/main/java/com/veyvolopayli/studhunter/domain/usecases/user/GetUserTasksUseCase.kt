package com.veyvolopayli.studhunter.domain.usecases.user

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.repository.UserRepository
import com.veyvolopayli.studhunter.domain.model.WideTask
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserTasksUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val prefs: SharedPreferences
) {
    operator fun invoke(userId: String, userStatus: String, taskStatus: String) = flow<Resource<List<WideTask>>> {
        try {
            val token = prefs.getString(Constants.JWT, null) ?: run {
                emit(Resource.Error(ErrorType.Unauthorized()))
                return@flow
            }
            val tasks = userRepository.getTasks(token = token, userId = userId, userStatus = userStatus, taskStatus = taskStatus)
            emit(Resource.Success(tasks))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(ErrorType.LocalError()))
        }
    }
}