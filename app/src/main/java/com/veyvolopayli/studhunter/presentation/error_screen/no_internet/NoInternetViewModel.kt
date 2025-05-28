package com.veyvolopayli.studhunter.presentation.error_screen.no_internet

import androidx.lifecycle.ViewModel
import com.veyvolopayli.studhunter.domain.usecases.internet.CheckInternetConnectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoInternetViewModel @Inject constructor(
    private val checkInternetConnectionUseCase: CheckInternetConnectionUseCase
) : ViewModel() {

}