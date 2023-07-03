package com.veyvolopayli.studhunter.presentation.profile_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.User
import com.veyvolopayli.studhunter.domain.usecases.user.FetchUserByIdUseCase
import com.veyvolopayli.studhunter.domain.usecases.user.GetCurrentUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val fetchUserByIdUseCase: FetchUserByIdUseCase
) : ViewModel() {

    private val _user = MutableLiveData<Resource<User>>()
    val user: LiveData<Resource<User>> = _user

    init {
        getCurrentUserId()
    }

    private fun getCurrentUserId() {
        getCurrentUserIdUseCase().onEach { id ->
            id?.let {
                getUserById(it)
            }
        }.launchIn(viewModelScope)
    }

    private fun getUserById(userId: String) {
        fetchUserByIdUseCase(userId).onEach { resource ->
            _user.value = resource
        }.launchIn(viewModelScope)
    }
}