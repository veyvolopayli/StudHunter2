package com.veyvolopayli.studhunter.presentation.sign_in_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.requests.SignInRequest
import com.veyvolopayli.studhunter.domain.usecases.auth.SignInByEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInByEmailUseCase: SignInByEmailUseCase
): ViewModel() {

    private val _state = MutableLiveData(SignInState())
    val state: LiveData<SignInState> = _state

    fun signIn(signInRequest: SignInRequest) {
        signInByEmailUseCase(signInRequest).onEach { result ->

            when (result) {
                is Resource.Success -> {
                    _state.value = SignInState(username = result.data?.token ?: "")
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }

        }.launchIn(viewModelScope)
    }

}