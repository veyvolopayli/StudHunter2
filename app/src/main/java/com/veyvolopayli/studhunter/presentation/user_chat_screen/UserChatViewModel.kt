package com.veyvolopayli.studhunter.presentation.user_chat_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.OutgoingMessageDTO
import com.veyvolopayli.studhunter.domain.model.Message
import com.veyvolopayli.studhunter.domain.repository.UserChatRepository
import com.veyvolopayli.studhunter.domain.usecases.user.GetCurrentUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class UserChatViewModel @Inject constructor(
    private val repository: UserChatRepository,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase
) : ViewModel() {

    private val _chatMessagesState = MutableLiveData(listOf<Message>())
    val chatMessagesState: LiveData<List<Message>> = _chatMessagesState

    private val _toastEvent = MutableLiveData<String>()
    val toastEvent: LiveData<String> = _toastEvent

    private val _currentUserID = MutableLiveData<String>()
    val currentUserID: LiveData<String> = _currentUserID

    init {
        getCurrentUserID()
    }

    fun disconnect() {
        viewModelScope.launch {
            repository.disconnect()
        }
    }

    fun sendMessage(messageBody: String, type: String) {
        viewModelScope.launch {
            val message = OutgoingMessageDTO(messageBody = messageBody, type = type)
            val jsonMessage = Gson().toJson(message)
            repository.sendMessage(jsonMessage)
        }
    }

    fun observeMessagesFromNewChat(publicationID: String) {
        viewModelScope.launch {
            when (repository.initSessionForNew(pubID = publicationID)) {
                is Resource.Success -> {
                    repository.observeMessages().onEach { message: Message ->
                        _chatMessagesState.value = chatMessagesState.value?.toMutableList()?.apply { add(0, message) }
//                        toastEvent(message.messageBody)
                        Log.e("MESSAGE", message.messageBody)
                    }.launchIn(viewModelScope)
                }
                is Resource.Error -> {
                    toastEvent("ERROR")
                    Log.e("MESSAGE", "ERROR")
                }
            }
        }
    }

    fun observeMessagesFromExistingChat(chatID: String) {
        viewModelScope.launch {
            when (repository.initSession(chatID = chatID)) {
                is Resource.Success -> {
                    repository.observeMessages().onEach { message: Message ->
                        _chatMessagesState.value = chatMessagesState.value?.toMutableList()?.apply { add(0, message) }
//                        toastEvent(message.messageBody)
                        Log.e("MESSAGE", message.messageBody)
                    }.launchIn(viewModelScope)
                }
                is Resource.Error -> {
                    Log.e("MESSAGE", "ERROR")
                    toastEvent("ERROR")
                }
            }
        }
    }

    private fun toastEvent(message: String) {
        _toastEvent.value = message
    }

    private fun getCurrentUserID() {
        getCurrentUserIdUseCase().onEach { userID ->
            userID?.let { _currentUserID.value = it }
        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
    }

}