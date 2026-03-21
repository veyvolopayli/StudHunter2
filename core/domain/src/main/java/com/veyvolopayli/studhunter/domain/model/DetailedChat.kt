package com.veyvolopayli.studhunter.domain.model

data class DetailedChat(
    val chatId: String,
    val sellerId: String,
    val avatar: String,
    val lastMessage: String,
    val publicationTitle: String,
    val timestamp: Long
)