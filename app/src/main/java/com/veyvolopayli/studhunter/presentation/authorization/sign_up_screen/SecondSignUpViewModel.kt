package com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.veyvolopayli.studhunter.presentation.authorization.AuthorizationResult
//import com.veyvolopayli.studhunter.domain.model.requests.SignUpRequest
//import com.veyvolopayli.studhunter.domain.usecases.auth.SignUpByEmailUseCase
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.launchIn
//import kotlinx.coroutines.flow.onEach
//
//@HiltViewModel
//class SecondSignUpViewModel(
//    private val signUpByEmailUseCase: SignUpByEmailUseCase
//) : ViewModel() {
//
//    private val _signUpState = MutableLiveData(SignUpState())
//    val signUpState: LiveData<SignUpState> = _signUpState
//
//    private val _signUpSecondState = MutableLiveData(SignUpSecondState())
//    val signUpSecondState: LiveData<SignUpSecondState> = _signUpSecondState
//
//    private val _signUpResult = MutableLiveData<AuthorizationResult<Unit>>()
//    val signUpResult: LiveData<AuthorizationResult<Unit>> = _signUpResult
//
//    init {
//
//    }
//
//    fun cacheNecessaryData(necessarySignUpData: NecessarySignUpData) {
//        _signUpState.value = SignUpState(
//            username = necessarySignUpData.username ?: "",
//            password = necessarySignUpData.password ?: "",
//            email = necessarySignUpData.email ?: ""
//        )
//    }
//
//    fun signUp(signUpRequest: SignUpRequest) {
//        signUpByEmailUseCase(signUpRequest).onEach { result ->
//            _signUpResult.value = when (result) {
//                is AuthorizationResult.Authorized -> AuthorizationResult.Authorized()
//                is AuthorizationResult.WrongData -> AuthorizationResult.WrongData()
//                is AuthorizationResult.UnknownError -> AuthorizationResult.UnknownError()
//                is AuthorizationResult.Error -> AuthorizationResult.UnknownError()
//            }
//        }.launchIn(viewModelScope)
//    }
//
//}