package com.veyvolopayli.studhunter.presentation.create_publication_screen

sealed class CreatePublicationEvent {
    object Uploading : CreatePublicationEvent()
    object Uploaded : CreatePublicationEvent()
    object Error : CreatePublicationEvent()
}
