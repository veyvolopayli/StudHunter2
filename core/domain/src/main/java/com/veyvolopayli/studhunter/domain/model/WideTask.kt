package com.veyvolopayli.studhunter.domain.model

import com.veyvolopayli.studhunter.domain.model.chat.Task
import kotlinx.serialization.Serializable

@Serializable
data class WideTask(
    val task: Task,
    val executor: User,
    val publication: Publication
)