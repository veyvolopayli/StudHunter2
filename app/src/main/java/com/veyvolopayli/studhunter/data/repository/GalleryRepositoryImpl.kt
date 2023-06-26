package com.veyvolopayli.studhunter.data.repository

import android.content.ContentResolver
import android.provider.MediaStore
import com.veyvolopayli.studhunter.domain.repository.GalleryRepository

class GalleryRepositoryImpl(private val contentResolver: ContentResolver) : GalleryRepository {
    override fun getImages(): List<String> {
        val imagePaths = mutableListOf<String>()
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )

        cursor?.use { c ->
            val columnIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            while (c.moveToNext()) {
                val path = c.getString(columnIndex)
                imagePaths.add(path)
            }
        }

        return imagePaths.reversed()
    }
}