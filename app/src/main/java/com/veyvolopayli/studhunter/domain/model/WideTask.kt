package com.veyvolopayli.studhunter.domain.model

import com.veyvolopayli.studhunter.domain.model.chat.Task

data class WideTask(
    val task: Task,
    val executor: User,
    val publication: Publication
)