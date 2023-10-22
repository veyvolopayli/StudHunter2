package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.domain.model.DetailedChat

interface ChatsRepository {
    suspend fun getChats(token: String): List<DetailedChat>
}