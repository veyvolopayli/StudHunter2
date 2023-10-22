package com.veyvolopayli.studhunter.data.repository

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.data.remote.StudHunterApi
import com.veyvolopayli.studhunter.data.remote.dto.Chat
import com.veyvolopayli.studhunter.domain.model.DetailedChat
import com.veyvolopayli.studhunter.domain.repository.ChatsRepository
import javax.inject.Inject

class ChatsRepositoryImpl @Inject constructor(
    private val api: StudHunterApi
) : ChatsRepository {
    override suspend fun getChats(token: String): List<DetailedChat> {
        return api.getChats(token)
    }
}