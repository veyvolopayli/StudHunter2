package com.veyvolopayli.studhunter.presentation.home_screen

sealed class HomeEvent {
    object Loading : HomeEvent()
    object Success : HomeEvent()
    object Error : HomeEvent()
}
