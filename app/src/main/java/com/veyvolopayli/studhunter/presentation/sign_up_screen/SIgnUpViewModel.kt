package com.veyvolopayli.studhunter.presentation.sign_up_screen

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.AuthResult
import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
import com.veyvolopayli.studhunter.domain.usecases.auth.SignUpByEmailUseCase
import com.veyvolopayli.studhunter.presentation.home_screen.HomeFragment
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SIgnUpViewModel @Inject constructor(
    private val signUpByEmailUseCase: SignUpByEmailUseCase
) : ViewModel() {

    private val _state = MutableLiveData(SignUpState())
    val state: LiveData<SignUpState> = _state

    fun signUp(signUpRequest: SignUpRequest, context: Context, activity: FragmentActivity) {
        signUpByEmailUseCase(signUpRequest).onEach { result ->
            when (result) {
                is AuthResult.Loading -> {
                    _state.value = SignUpState(isLoading = true)
                }
                is AuthResult.Authorized -> {
                    activity.supportFragmentManager.commit {
                        replace(R.id.main_fragment_container, HomeFragment())
                    }
                    _state.value = SignUpState(isLoading = false)
                }
                is AuthResult.Unauthorized -> {
                    _state.value = SignUpState(isLoading = false)
                    Toast.makeText(context, "Unauthorized", Toast.LENGTH_SHORT).show()
                }
                is AuthResult.WrongPassword -> {
                    _state.value = SignUpState(isLoading = false)
                    Toast.makeText(context, "WrongPassword", Toast.LENGTH_SHORT).show()

                }
                is AuthResult.UnknownError -> {
                    _state.value = SignUpState(isLoading = false)
                    Toast.makeText(context, "UnknownError", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

}