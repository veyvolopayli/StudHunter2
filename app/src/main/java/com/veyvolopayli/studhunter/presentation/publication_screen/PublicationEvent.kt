package com.veyvolopayli.studhunter.presentation.publication_screen

sealed class PublicationEvent<T>(val data: T? = null) {
    class ImagesLoaded<T>(data: T?) : PublicationEvent<T>(data)
    class DataLoaded<T>(data: T?) : PublicationEvent<T>(data)
    class UserLoaded<T>(data: T?) : PublicationEvent<T>(data)
}