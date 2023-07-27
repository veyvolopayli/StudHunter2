package com.veyvolopayli.studhunter.presentation.user_chat_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserChatViewModel @Inject constructor(

) : ViewModel() {

    private val _chatMessagesState = MutableLiveData(emptyMap<String, String>())
    val chatMessagesState: LiveData<Map<String, String>> = _chatMessagesState



}