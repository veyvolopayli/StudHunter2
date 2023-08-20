package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.data.remote.dto.Chat

interface ChatsRepository {
    suspend fun getChats(): List<Chat>
}