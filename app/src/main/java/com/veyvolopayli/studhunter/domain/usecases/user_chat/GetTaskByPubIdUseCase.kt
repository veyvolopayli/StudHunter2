package com.veyvolopayli.studhunter.domain.usecases.user_chat

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.chat.Task
import com.veyvolopayli.studhunter.domain.repository.PrefsRepository
import com.veyvolopayli.studhunter.domain.repository.UserChatRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetTaskByPubIdUseCase @Inject constructor(
    private val repository: UserChatRepository,
    private val prefsRepository: PrefsRepository
) {

    operator fun invoke(pubId: String) = flow<Resource<Task?>> {
        try {
            val token = prefsRepository.getJwtToken() ?: run {
                emit(Resource.Error(ErrorType.Unauthorized()))
                return@flow
            }
            val task = repository.getTaskByPubId(token = token, pubId = pubId)
            emit(Resource.Success(task))
        } catch (e: HttpException) {
            if (e.code() == 409) {
                e.printStackTrace()
                emit(Resource.Success(data = null))
            } else {
                e.printStackTrace()
                emit(Resource.Error(ErrorType.ServerError()))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(ErrorType.LocalError()))
        }
    }

}