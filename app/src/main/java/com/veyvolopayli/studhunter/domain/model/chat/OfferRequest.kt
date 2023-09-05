package com.veyvolopayli.studhunter.domain.model.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("OutgoingDealRequest")
data class OfferRequest(
    val jobDeadline: Long
) : DataTransfer