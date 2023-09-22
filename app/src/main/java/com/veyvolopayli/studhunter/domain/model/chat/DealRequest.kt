package com.veyvolopayli.studhunter.domain.model.chat

import com.veyvolopayli.studhunter.domain.model.chat.DataTransfer
import io.ktor.util.date.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("DealRequest")
data class DealRequest(
    val jobTime: Long
) : DataTransfer