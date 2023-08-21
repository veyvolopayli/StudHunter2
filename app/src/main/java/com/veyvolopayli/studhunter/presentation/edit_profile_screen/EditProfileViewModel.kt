package com.veyvolopayli.studhunter.presentation.edit_profile_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.usecases.edit_profile.EditProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val editProfileUseCase: EditProfileUseCase
) : ViewModel() {

    private val _result = MutableLiveData<EditProfileState>()
    val result: LiveData<EditProfileState> = _result

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    fun editProfile(name: String, surname: String, university: String) {

        _loading.value = true
        editProfileUseCase(name, surname, university).onEach { resource ->
            when(resource) {
                is Resource.Success -> {
                    _loading.value = false
                    _result.value = EditProfileState(name, surname, university)
                }
                is Resource.Error -> {
                    _loading.value = false
                    when(resource.error) {
                        is ErrorType.LocalError -> {
                            _error.value = "Что-то пошло не так"
                        }
                        is ErrorType.ServerError -> {
                            _error.value = "Упс... Сервер, кажется, прилег отдохнуть"
                        }
                        is ErrorType.NetworkError -> {
                            _error.value = "Проверьте подключение к интернету"
                        }
                        is ErrorType.Unauthorized -> {
                            _error.value = "Авторизуйтесь"
                        }
                        null -> { }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

}