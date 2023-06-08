package com.veyvolopayli.studhunter.presentation.publication_screen

sealed class PublicationEvent {
    object Loading : PublicationEvent()
    object Success : PublicationEvent()
    object Error : PublicationEvent()
}