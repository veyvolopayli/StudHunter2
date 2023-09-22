package com.veyvolopayli.studhunter.domain.model

sealed class TextFrameType(val data: Any) {
    class TMessage(data: Message) : TextFrameType(data)
    class TOther(data: Unit) : TextFrameType(data)
}
