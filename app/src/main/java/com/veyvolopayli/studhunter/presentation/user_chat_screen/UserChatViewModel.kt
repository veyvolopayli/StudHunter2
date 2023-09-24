package com.veyvolopayli.studhunter.presentation.user_chat_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.MessageDTO
import com.veyvolopayli.studhunter.domain.model.Message
import com.veyvolopayli.studhunter.domain.model.chat.DealRequest
import com.veyvolopayli.studhunter.domain.model.chat.OutgoingMessage
import com.veyvolopayli.studhunter.domain.model.chat.Task
import com.veyvolopayli.studhunter.domain.repository.UserChatRepository
import com.veyvolopayli.studhunter.domain.usecases.user.GetCurrentUserIdUseCase
import com.veyvolopayli.studhunter.domain.usecases.user_chat.GetMessagesByChatIdUseCase
import com.veyvolopayli.studhunter.domain.usecases.user_chat.GetMessagesByPublicationIdUseCase
import com.veyvolopayli.studhunter.domain.usecases.user_chat.GetTaskByChatIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserChatViewModel @Inject constructor(
    private val repository: UserChatRepository,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val getMessagesByChatIdUseCase: GetMessagesByChatIdUseCase,
    private val getMessagesByPublicationIdUseCase: GetMessagesByPublicationIdUseCase,
    private val getTaskByChatIdUseCase: GetTaskByChatIdUseCase
) : ViewModel() {

    private val _chatMessagesState = MutableLiveData(listOf<Message>())
    val chatMessagesState: LiveData<List<Message>> = _chatMessagesState

    private val _toastEvent = MutableLiveData<String>()
    val toastEvent: LiveData<String> = _toastEvent

    private val _currentUserID = MutableLiveData<String>()
    val currentUserID: LiveData<String> = _currentUserID

    private val _dealRequestState = MutableLiveData<DealRequest>()
    val dealRequestState: LiveData<DealRequest> = _dealRequestState

    private val _task = MutableLiveData<Task>()
    val task: LiveData<Task> = _task

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
            val message = OutgoingMessage(messageBody = messageBody, messageType = type)
            repository.sendMessage(message)
        }
    }

    fun getMessagesByChatId(chatId: String) {
        getMessagesByChatIdUseCase(chatId = chatId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { messages ->
                        _chatMessagesState.value = messages
                    }
                }

                is Resource.Error -> {
                    when (resource.error ?: ErrorType.LocalError()) {
                        is ErrorType.LocalError -> {
                            toastEvent("Error")
                        }

                        is ErrorType.ServerError -> {
                            toastEvent("Error")
                        }

                        is ErrorType.NetworkError -> {
                            toastEvent("Error")
                        }

                        is ErrorType.Unauthorized -> {
                            toastEvent("Error")
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMessagesByPublicationId(publicationId: String) {
        getMessagesByPublicationIdUseCase(pubId = publicationId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { messages ->
                        _chatMessagesState.value = messages
                    }
                }

                is Resource.Error -> {
                    when (resource.error ?: ErrorType.LocalError()) {
                        is ErrorType.LocalError -> {
                            toastEvent("Error")
                        }

                        is ErrorType.ServerError -> {
                            toastEvent("Error")
                        }

                        is ErrorType.NetworkError -> {
                            toastEvent("Error")
                        }

                        is ErrorType.Unauthorized -> {
                            toastEvent("Error")
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun observeMessagesFromNewChat(publicationID: String) {
        viewModelScope.launch {
            when (repository.initSessionForNew(pubID = publicationID)) {
                is Resource.Success -> {
                    repository.observeMessages().onEach { incomingTextFrame ->
                        when (incomingTextFrame.type) {
                            TRANSFERRING_TYPE_MESSAGE -> {
                                try {
                                    val messageDTO = incomingTextFrame.data as? MessageDTO
                                        ?: throw Exception("Frame is not MessageDTO")
                                    _chatMessagesState.value =
                                        chatMessagesState.value?.toMutableList()
                                            ?.apply { add(0, messageDTO.toMessage()) }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    _toastEvent.value = "ERROR"
                                }
                            }

                            TRANSFERRING_TYPE_DEAL_REQUEST -> {
                                try {
                                    val dealRequest = incomingTextFrame.data as? DealRequest
                                        ?: throw Exception("Frame is not OfferRequestDTO")
                                    _dealRequestState.value = dealRequest
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    _toastEvent.value = "ERROR"
                                }
                            }

                            TRANSFERRING_TYPE_TASK -> {
                                try {
                                    val task = incomingTextFrame.data as? Task
                                        ?: throw Exception("Frame is not OfferResponseDTO")
                                    _task.value = task
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
                        when (incomingTextFrame.type) {
                            TRANSFERRING_TYPE_MESSAGE -> {
                                try {
                                    val messageDTO = incomingTextFrame.data as? MessageDTO
                                        ?: throw Exception("Frame is not MessageDTO")
                                    _chatMessagesState.value =
                                        chatMessagesState.value?.toMutableList()
                                            ?.apply { add(0, messageDTO.toMessage()) }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    _toastEvent.value = "ERROR"
                                }
                            }

                            TRANSFERRING_TYPE_DEAL_REQUEST -> {
                                try {
                                    val dealRequest = incomingTextFrame.data as? DealRequest
                                        ?: throw Exception("Frame is not OfferRequestDTO")
                                    _dealRequestState.value = dealRequest
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    _toastEvent.value = "ERROR"
                                }
                            }

                            TRANSFERRING_TYPE_TASK -> {
                                try {
                                    val task = incomingTextFrame.data as? Task
                                            ?: throw Exception("Frame is not OfferResponseDTO")
                                    _task.value = task
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

    fun sendOfferResponse(isAccepted: Boolean) {
        _task.value?.let {
            viewModelScope.launch {
                val outgoingTask = it.copy(status = if (isAccepted) "accepted" else "declined")
                repository.sendOfferResponse(outgoingTask)
            }
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

    fun getTask(chatId: String) {
        getTaskByChatIdUseCase(chatId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let {
                        _task.value = it
                    }
                }
                is Resource.Error -> {
                    _toastEvent.value = "Getting task error"
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
    }

    private companion object {
        private const val TRANSFERRING_TYPE_MESSAGE = "message"
        private const val TRANSFERRING_TYPE_DEAL_REQUEST = "deal_request"
        private const val TRANSFERRING_TYPE_TASK = "task"
    }

}