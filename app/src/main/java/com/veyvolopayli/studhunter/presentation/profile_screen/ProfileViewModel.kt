package com.veyvolopayli.studhunter.presentation.profile_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.User
import com.veyvolopayli.studhunter.domain.usecases.auth.ClearAuthDataUseCase
import com.veyvolopayli.studhunter.domain.usecases.user.FetchUserByIdUseCase
import com.veyvolopayli.studhunter.domain.usecases.user.GetCurrentUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val fetchUserByIdUseCase: FetchUserByIdUseCase,
    private val clearAuthDataUseCase: ClearAuthDataUseCase
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> = _loading

    private val _authorized = MutableLiveData<Boolean>()
    val authorized: LiveData<Boolean> = _authorized

    init {
        getCurrentUserId()
    }

    private fun getCurrentUserId() {
        getCurrentUserIdUseCase().onEach { id ->
            getUserById(id)
        }.launchIn(viewModelScope)
    }

    private fun getUserById(userId: String?) {
        fetchUserByIdUseCase(userId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { user ->
                        _user.value = user
                    }
                }

                is Resource.Error -> {
                    when (resource.error) {
                        is ErrorType.LocalError -> {

                        }

                        is ErrorType.ServerError -> {

                        }

                        is ErrorType.NetworkError -> {

                        }

                        is ErrorType.Unauthorized -> {
                            _authorized.value = false
                        }

                        else -> {

                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun logout() {
        clearAuthDataUseCase()
    }

    fun updateUser(name: String, surname: String, university: String) {
        _user.value = user.value?.copy(name = name, surname = surname, university = university)
    }
}