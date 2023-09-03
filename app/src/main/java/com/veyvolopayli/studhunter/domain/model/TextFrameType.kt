package com.veyvolopayli.studhunter.domain.model

sealed class TextFrameType(val data: Any) {
    class TMessage(data: Message) : TextFrameType(data)
    class TOfferRequest(data: OfferRequestDTO) : TextFrameType(data)
    class TOfferResponse(data: OfferResponseDTO) : TextFrameType(data)
    class TOther(data: Unit) : TextFrameType(data)
}
