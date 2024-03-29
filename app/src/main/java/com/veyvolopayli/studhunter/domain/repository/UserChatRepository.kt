package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.MessageDTO
import com.veyvolopayli.studhunter.domain.model.chat.IncomingTextFrame
import com.veyvolopayli.studhunter.domain.model.chat.OutgoingMessage
import com.veyvolopayli.studhunter.domain.model.chat.Task
import kotlinx.coroutines.flow.Flow

interface UserChatRepository {
    suspend fun initSessionForNew(pubID: String): Resource<Unit>
    suspend fun initSession(chatID: String): Resource<Unit>
    suspend fun sendMessage(message: OutgoingMessage)
    suspend fun disconnect()
    fun observeMessages(): Flow<IncomingTextFrame>
    suspend fun sendOfferRequest(jobDeadline: Long)
    suspend fun sendOfferResponse(task: Task)
    suspend fun getMessagesByChatId(token: String, chatId: String): List<MessageDTO>
    suspend fun getMessagesByPublicationId(token: String, pubId: String): List<MessageDTO>
    suspend fun getTaskByChatId(token: String, chatId: String): Task
    suspend fun getTaskByPubId(token: String, pubId: String): Task
}