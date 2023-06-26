package com.veyvolopayli.studhunter.presentation.create_publication_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.PublicationToUpload
import com.veyvolopayli.studhunter.domain.usecases.create_publication.GetPriceTypesUseCase
import com.veyvolopayli.studhunter.domain.usecases.create_publication.UploadPublicationUseCase
import com.veyvolopayli.studhunter.domain.usecases.user.GetCurrentUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CreatePublicationViewModel @Inject constructor(
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val uploadPublicationUseCase: UploadPublicationUseCase,
    private val getPriceTypesUseCase: GetPriceTypesUseCase
) : ViewModel() {

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    private val _state = MutableLiveData<CreatePublicationState>()
    val state: LiveData<CreatePublicationState> = _state

    private val _selectedImages = MutableLiveData<List<String>>()
    val selectedImages: LiveData<List<String>> = _selectedImages

    private val _priceTypes = MutableLiveData<Map<Int, String>>()
    val priceTypes: LiveData<Map<Int, String>> = _priceTypes

    init {
        getUserId()
        getPriceTypes()
    }

    private fun getUserId() {
        getCurrentUserIdUseCase().onEach { id ->
            _userId.value = id
        }.launchIn(viewModelScope)
    }

    private fun getPriceTypes() {
        getPriceTypesUseCase().onEach { types ->
            types?.let {
                _priceTypes.value = it
            }
        }.launchIn(viewModelScope)
    }

    fun uploadPublication(images: List<String>, publicationToUpload: PublicationToUpload) {
        uploadPublicationUseCase(images, publicationToUpload).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _state.value = CreatePublicationState.Uploading
                }
                is Resource.Success -> {
                    _state.value = CreatePublicationState.UploadSuccess
                }
                is Resource.Error -> {
                    _state.value = CreatePublicationState.Error
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setSelectedImages(selectedImages: List<String>) {
        _selectedImages.value = selectedImages
    }

}

sealed class CreatePublicationState {
    object Uploading : CreatePublicationState()
    object UploadSuccess : CreatePublicationState()
    object Error : CreatePublicationState()
}