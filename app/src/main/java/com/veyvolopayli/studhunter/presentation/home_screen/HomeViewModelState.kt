package com.veyvolopayli.studhunter.presentation.home_screen

import com.veyvolopayli.studhunter.domain.model.Publication

data class HomeViewModelState(
    val isLoading: Boolean = false,
    val publications: List<Publication> = emptyList(),
    val error: String = ""
)