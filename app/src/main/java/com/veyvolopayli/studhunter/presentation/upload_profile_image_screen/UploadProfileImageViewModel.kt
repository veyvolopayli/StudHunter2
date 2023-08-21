package com.veyvolopayli.studhunter.presentation.upload_profile_image_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.domain.usecases.edit_profile.UploadProfileImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UploadProfileImageViewModel @Inject constructor(
    private val uploadProfileImageUseCase: UploadProfileImageUseCase
) : ViewModel() {

    private val _chosenImage = MutableLiveData<String>()
    val chosenImage: LiveData<String> = _chosenImage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun uploadProfileImage() {
        _loading.value = true
        _chosenImage.value?.let { image ->
            uploadProfileImageUseCase(image).onEach {
                _isSuccess.value = it != null
                _loading.value = false
            }.launchIn(viewModelScope)
        }
    }

    fun setChosenImage(imageUri: String) {
        _chosenImage.value = imageUri
    }
}