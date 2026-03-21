package com.veyvolopayli.studhunter.domain.usecases.gallery

import com.veyvolopayli.studhunter.domain.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetUserImagesUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository
) {
    operator fun invoke() : Flow<List<String>?> = flow {
        try {
            val images = galleryRepository.getImages()
            emit(images)
        } catch (e: Exception) {
            emit(null)
        }
    }
}