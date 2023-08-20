package com.veyvolopayli.studhunter.presentation.my_publications_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.MyPublicationDTO
import com.veyvolopayli.studhunter.domain.model.Publication
import com.veyvolopayli.studhunter.domain.usecases.my_publications.GetMyPublicationsUseCase
import com.veyvolopayli.studhunter.domain.usecases.user_publicaitons.GetUserPublicationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MyPublicationsViewModel @Inject constructor(
    private val getMyPublicationsUseCase: GetMyPublicationsUseCase
) : ViewModel() {

    private val _myPublications = MutableLiveData<List<MyPublicationDTO>>()
    val myPublications: LiveData<List<MyPublicationDTO>> = _myPublications

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        getUserPublications()
    }

    private fun getUserPublications() {
        getMyPublicationsUseCase().onEach { resource ->
            when(resource) {
                is Resource.Success -> {
                    _myPublications.value = resource.data ?: emptyList()
                }
                is Resource.Error -> {
                    when(resource.error ?: ErrorType.LocalError()) {
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
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

}