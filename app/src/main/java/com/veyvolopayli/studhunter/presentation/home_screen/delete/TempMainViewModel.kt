package com.veyvolopayli.studhunter.presentation.home_screen.delete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TempMainViewModel : ViewModel() {

    private val _state = MutableLiveData<List<User>>(emptyList())
    val state: LiveData<List<User>> = _state

//    private var _recyclerState: Parcelable? = null
//    val recyclerState = _recyclerState

    init {
        viewModelScope.launch {
            delay(3000)
            _state.value = users
        }
    }

//    fun saveRecyclerState(state: Parcelable?) {
//        _recyclerState = state
//    }

    companion object {
        val users = listOf(
            User("Имя", 18),
            User("Антон", 11),
            User("Арсен", 17),
            User("Сларк", 10),
            User("Артем", 18),
            User("Михаил", 21),
            User("Сулхан", 22),
            User("Самад", 21),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67),
            User("Илья", 67)
        )
    }

}