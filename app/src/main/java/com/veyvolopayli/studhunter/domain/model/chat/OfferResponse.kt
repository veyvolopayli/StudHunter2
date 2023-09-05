package com.veyvolopayli.studhunter.domain.model.chat

import com.veyvolopayli.studhunter.domain.model.chat.DataTransfer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("OutgoingDealResponse")
data class OfferResponse(
    val accepted: Boolean
) : DataTransfer