package com.veyvolopayli.studhunter.presentation.chats_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.Chat
import com.veyvolopayli.studhunter.domain.usecases.chats.GetChatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase
) : ViewModel() {

    private val _chats = MutableLiveData<List<Chat>>()
    val chats: LiveData<List<Chat>> = _chats

    private val _toastEvent = MutableLiveData<String>()
    val toastEvent: LiveData<String> = _toastEvent

    init {
        getChats()
    }

    fun getChats() {
        getChatsUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let {
                        _chats.value = it
                    }
                }
                is Resource.Error -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun toastEvent(message: String) {
        _toastEvent.value = message
    }

}