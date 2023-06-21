package com.veyvolopayli.studhunter.presentation.image_gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.domain.repository.GalleryRepository
import com.veyvolopayli.studhunter.domain.usecases.gallery.GetUserImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getUserImagesUseCase: GetUserImagesUseCase
) : ViewModel() {

    private val _images = MutableLiveData<List<String>>()
    val images: LiveData<List<String>> = _images

    init {
        getImages()
    }

    private fun getImages() {
        getUserImagesUseCase().onEach { images ->
            if (images != null) {
                _images.value = images
            }
        }.launchIn(viewModelScope)
    }
}