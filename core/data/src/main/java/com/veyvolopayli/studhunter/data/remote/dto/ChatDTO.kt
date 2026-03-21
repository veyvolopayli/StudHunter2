package com.veyvolopayli.studhunter.data.remote.dto

data class Chat(
    val id: String,
    val publicationId: String,
    val customerId: String,
    val sellerId: String,
    val lastMessage: String,
    val timestamp: Long
)
