package com.veyvolopayli.studhunter.presentation.user_chat_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.veyvolopayli.studhunter.domain.model.OfferRequest
import com.veyvolopayli.studhunter.domain.model.OfferResponse
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.MessageDTO
import com.veyvolopayli.studhunter.data.remote.dto.OutgoingMessageDTO
import com.veyvolopayli.studhunter.domain.model.Message
import com.veyvolopayli.studhunter.domain.model.OfferRequestDTO
import com.veyvolopayli.studhunter.domain.model.OfferResponseDTO
import com.veyvolopayli.studhunter.domain.model.TextFrameType
import com.veyvolopayli.studhunter.domain.repository.UserChatRepository
import com.veyvolopayli.studhunter.domain.usecases.user.GetCurrentUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.lang.Exception
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

    private val _dealRequestState = MutableLiveData<OfferRequestDTO>()
    val dealRequestState: LiveData<OfferRequestDTO> = _dealRequestState

    private val _dealResponseState = MutableLiveData<OfferResponseDTO>()
    val dealResponseState: LiveData<OfferResponseDTO> = _dealResponseState

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
                    repository.observeMessages().onEach { incomingTextFrame ->
                        when(incomingTextFrame.type) {
                            INCOMING_TYPE_MESSAGE -> {
                                try {
                                    val messageDTO = Json.decodeFromString<MessageDTO>(incomingTextFrame.jsonStringObj)
                                    _chatMessagesState.value = chatMessagesState.value?.toMutableList()?.apply { add(0, messageDTO.toMessage()) }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    _toastEvent.value = "ERROR"
                                }
                            }
                            INCOMING_TYPE_DEAL_REQUEST -> {
                                try {
                                    val dealRequestDTO = Json.decodeFromString<OfferRequestDTO>(incomingTextFrame.jsonStringObj)
                                    _dealRequestState.value = dealRequestDTO
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    _toastEvent.value = "ERROR"
                                }
                            }
                            INCOMING_TYPE_DEAL_RESPONSE -> {
                                try {
                                    val dealResponseDTO = Json.decodeFromString<OfferResponseDTO>(incomingTextFrame.jsonStringObj)
                                    _dealResponseState.value = dealResponseDTO
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    _toastEvent.value = "ERROR"
                                }
                            }
                        }
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
                    /*repository.observeMessages().onEach { message: Message ->
                        _chatMessagesState.value = chatMessagesState.value?.toMutableList()?.apply { add(0, message) }
//                        toastEvent(message.messageBody)
                        Log.e("MESSAGE", message.messageBody)
                    }.launchIn(viewModelScope)*/

                    repository.observeMessages().onEach { incomingTextFrame ->
                        when(incomingTextFrame.type) {
                            INCOMING_TYPE_MESSAGE -> {
                                try {
                                    val messageDTO = Json.decodeFromString<MessageDTO>(incomingTextFrame.jsonStringObj)
                                    _chatMessagesState.value = chatMessagesState.value?.toMutableList()?.apply { add(0, messageDTO.toMessage()) }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    _toastEvent.value = "ERROR"
                                }
                            }
                            INCOMING_TYPE_DEAL_REQUEST -> {
                                try {
                                    val dealRequestDTO = Json.decodeFromString<OfferRequestDTO>(incomingTextFrame.jsonStringObj)
                                    _dealRequestState.value = dealRequestDTO
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    _toastEvent.value = "ERROR"
                                }
                            }
                            INCOMING_TYPE_DEAL_RESPONSE -> {
                                try {
                                    val dealResponseDTO = Json.decodeFromString<OfferResponseDTO>(incomingTextFrame.jsonStringObj)
                                    _dealResponseState.value = dealResponseDTO
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    _toastEvent.value = "ERROR"
                                }
                            }
                        }
                        /*when(textFrameType) {
                            is TextFrameType.TMessage -> {
                                val message = textFrameType.data as? Message
                                if (message != null) {
                                    _chatMessagesState.value = chatMessagesState.value?.toMutableList()?.apply { add(0, message) }
                                }
                            }
                            is TextFrameType.TOfferRequest -> {
                                val offerRequest = textFrameType.data as? OfferRequest
                                if (offerRequest != null) {
                                    _toastEvent.value = offerRequest.toString()
                                    _offerRequestState.value = offerRequest!!
                                }
                            }
                            is TextFrameType.TOfferResponse -> {
                                val offerResponse = textFrameType.data as? OfferResponse
                                if (offerResponse != null) {
                                    _toastEvent.value = offerResponse.toString()
                                    _offerResponseState.value = offerResponse!!
                                }
                            }
                            is TextFrameType.TOther -> {
                                _toastEvent.value = "error"
                            }
                        }*/

//                        toastEvent(message.messageBody)
//                        Log.e("MESSAGE", message.messageBody)
                    }.launchIn(viewModelScope)

                }
                is Resource.Error -> {
                    Log.e("MESSAGE", "ERROR")
                    toastEvent("ERROR")
                }
            }
        }
    }

    fun sendOfferResponse(accepted: Boolean) {
        viewModelScope.launch {
            repository.sendOfferResponse(accepted)
        }
    }

    fun sendOfferRequest(jobDeadline: Long) {
        viewModelScope.launch {
            repository.sendOfferRequest(jobDeadline = jobDeadline)
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

    private companion object {
        private const val INCOMING_TYPE_MESSAGE = "message"
        private const val INCOMING_TYPE_DEAL_REQUEST = "deal_request"
        private const val INCOMING_TYPE_DEAL_RESPONSE = "deal_response"
    }

}