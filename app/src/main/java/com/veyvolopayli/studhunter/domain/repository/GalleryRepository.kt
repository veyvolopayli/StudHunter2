package com.veyvolopayli.studhunter.domain.repository

interface GalleryRepository {
    fun getImages(): List<String>
}