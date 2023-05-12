package com.veyvolopayli.studhunter.presentation.sign_up_screen

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.usecases.auth.SignUpByEmailUseCase
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SIgnUpViewModel @Inject constructor(
    private val signUpByEmailUseCase: SignUpByEmailUseCase
) : ViewModel() {

    private val _state = MutableLiveData(SignUpState())
    val state: LiveData<SignUpState> = _state

    fun signUp(signUpRequest: SignUpRequest, context: Context) {
        signUpByEmailUseCase(signUpRequest).onEach { result ->
            when (result) {
                is AuthResult.Loading -> {

                }
                is AuthResult.Authorized -> {

                }
                is AuthResult.Unauthorized -> {

                }
                is AuthResult.WrongPassword -> {

                }
                is AuthResult.UnknownError -> {

                }
            }
        }
    }

}