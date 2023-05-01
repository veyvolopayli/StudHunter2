package com.veyvolopayli.studhunter.presentation.publications_list

import com.veyvolopayli.studhunter.domain.model.Publication

data class PublicationsListState(
    val isLoading: Boolean = false,
    val publications: List<Publication> = emptyList(),
    val error: String = ""
)
