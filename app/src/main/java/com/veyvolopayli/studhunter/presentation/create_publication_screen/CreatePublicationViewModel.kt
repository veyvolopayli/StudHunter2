package com.veyvolopayli.studhunter.presentation.create_publication_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.PublicationToUpload
import com.veyvolopayli.studhunter.domain.usecases.categories.GetCategoriesUseCase
import com.veyvolopayli.studhunter.domain.usecases.categories.GetDistrictsUseCase
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
    private val getPriceTypesUseCase: GetPriceTypesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    private val _state = MutableLiveData<Resource<String>>()
    val state: LiveData<Resource<String>> = _state

    private val _selectedImages = MutableLiveData<List<String>>()
    val selectedImages: LiveData<List<String>> = _selectedImages

    private val _priceTypes = MutableLiveData<Map<Int, String>>()
    val priceTypes: LiveData<Map<Int, String>> = _priceTypes

    private val _categories = MutableLiveData<Map<Int, String>>()
    val categories: LiveData<Map<Int, String>> = _categories

    private val _selectedDistrict = MutableLiveData<String>()
    val selectedDistrict: LiveData<String> = _selectedDistrict

    init {
        getUserId()
        getPriceTypes()
        getCategories()
    }

    private fun getCategories() {
        getCategoriesUseCase().onEach { pubCategories ->
            if (pubCategories is Resource.Success) {
                _categories.value = pubCategories.data ?: emptyMap()
            }
        }.launchIn(viewModelScope)
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
            _state.value = resource
        }.launchIn(viewModelScope)
    }

    fun setSelectedImages(selectedImages: List<String>) {
        _selectedImages.value = selectedImages
    }

    fun setSelectedDistrict(district: String) {
        _selectedDistrict.value = district
    }

}