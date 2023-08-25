package com.veyvolopayli.studhunter.presentation.gallery

sealed class GallerySelectMode {
    object Single : GallerySelectMode()
    object Multiple : GallerySelectMode()
}