package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.Message
import com.veyvolopayli.studhunter.domain.model.TextFrameType
import kotlinx.coroutines.flow.Flow

interface UserChatRepository {
    suspend fun initSessionForNew(pubID: String): Resource<Unit>
    suspend fun initSession(chatID: String): Resource<Unit>
    suspend fun sendMessage(message: String)
    suspend fun disconnect()
    fun observeMessages(): Flow<TextFrameType>
    suspend fun sendOfferRequest(jobDeadline: Long)
    suspend fun sendOfferResponse(accepted: Boolean)
}