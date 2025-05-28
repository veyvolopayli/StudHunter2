package com.veyvolopayli.studhunter.presentation.main

import com.veyvolopayli.studhunter.common.ErrorType

sealed interface UiState {
    data object Loading : UiState
    data object NoInternet : UiState
    data object UpdateAvailable : UiState
    data object Authorized : UiState
    data object NotAuthorized : UiState
    data class Error(val error: ErrorType?) : UiState
}
