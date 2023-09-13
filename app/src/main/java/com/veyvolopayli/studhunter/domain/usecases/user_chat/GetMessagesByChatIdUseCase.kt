package com.veyvolopayli.studhunter.domain.usecases.user_chat

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.Message
import com.veyvolopayli.studhunter.domain.repository.UserChatRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMessagesByChatIdUseCase @Inject constructor(
    private val prefs: SharedPreferences,
    private val repository: UserChatRepository
) {
    operator fun invoke(chatId: String) = flow<Resource<List<Message>>> {
        try {
            val token = prefs.getString(Constants.JWT, null) ?: run {
                emit(Resource.Error(ErrorType.Unauthorized()))
                return@flow
            }
            val messages = repository.getMessagesByChatId(token = token, chatId = chatId).map { it.toMessage() }.reversed()
            emit(Resource.Success(messages))
        } catch (e: Exception) {
            emit(Resource.Error(ErrorType.LocalError()))
        }
    }
}