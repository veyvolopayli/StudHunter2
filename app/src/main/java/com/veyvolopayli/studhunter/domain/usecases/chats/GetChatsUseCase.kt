package com.veyvolopayli.studhunter.domain.usecases.chats

import android.util.Log
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.Chat
import com.veyvolopayli.studhunter.domain.model.DetailedChat
import com.veyvolopayli.studhunter.domain.repository.ChatsRepository
import com.veyvolopayli.studhunter.domain.repository.PrefsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatsRepository: ChatsRepository,
    private val prefsRepository: PrefsRepository
) {
    operator fun invoke(): Flow<Resource<List<DetailedChat>>> = flow {
        try {
            val token = prefsRepository.getJwtToken() ?: run {
                emit(Resource.Error(ErrorType.Unauthorized()))
                return@flow
            }
            val chats = chatsRepository.getChats(token)
            emit(Resource.Success(data = chats))
        } catch (e: Exception) {
            Log.e("TAG", e.localizedMessage ?: "PIdARASS")
            emit(Resource.Error(ErrorType.ServerError()))
        }
    }
}