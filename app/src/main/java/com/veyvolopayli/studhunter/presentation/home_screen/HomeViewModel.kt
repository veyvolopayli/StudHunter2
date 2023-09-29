package com.veyvolopayli.studhunter.presentation.home_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.FilterRequest
import com.veyvolopayli.studhunter.domain.model.WideTask
import com.veyvolopayli.studhunter.domain.model.chat.Task
import com.veyvolopayli.studhunter.domain.usecases.get_publications.FetchPublicationsUseCase
import com.veyvolopayli.studhunter.domain.usecases.get_publications.GetFilteredPublicationsUseCase
import com.veyvolopayli.studhunter.domain.usecases.user.GetCurrentUserIdUseCase
import com.veyvolopayli.studhunter.domain.usecases.user.GetUserTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchPublicationsUseCase: FetchPublicationsUseCase,
    private val getFilteredPublicationsUseCase: GetFilteredPublicationsUseCase,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val getUserTasksUseCase: GetUserTasksUseCase
): ViewModel() {

    private val _state = MutableLiveData(HomeState())
    val state: LiveData<HomeState> = _state

    private val _event = MutableLiveData<HomeEvent>(HomeEvent.Loading)
    val event: LiveData<HomeEvent> = _event

    private val _tasksState = MutableLiveData<List<WideTask>>()
    val tasksState: LiveData<List<WideTask>> = _tasksState

//    private var _recyclerState: Parcelable? = null
//    val recyclerState = _recyclerState

    init {
        fetchPublications()
        getCurrentUserId()
    }

    private fun getCurrentUserId() {
        getCurrentUserIdUseCase().onEach { userId ->
            userId?.let {
                getActiveTasks(it)
                Log.e("USER_ID", it)
            }
        }.launchIn(viewModelScope)
    }

    private fun getActiveTasks(userId: String) {
        getUserTasksUseCase(userId = userId, userStatus = "executor", taskStatus = "accepted").onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _tasksState.value = resource.data ?: emptyList()
                }
                is Resource.Error -> {
                    when (resource.error ?: ErrorType.LocalError()) {
                        is ErrorType.LocalError -> {

                        }
                        is ErrorType.ServerError -> {

                        }
                        is ErrorType.NetworkError -> {

                        }
                        is ErrorType.Unauthorized -> {

                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun fetchPublications() {
        fetchPublicationsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = HomeState(publications = result.data ?: emptyList())
                    _event.value = HomeEvent.Success
                }
                is Resource.Error -> {
                    _state.value = HomeState(
                        error = ""
                    )
                    _event.value = HomeEvent.Error
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getFilteredPublications(filterRequest: FilterRequest) {
        getFilteredPublicationsUseCase(filterRequest).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _state.value = HomeState(publications = resource.data ?: emptyList())
                    _event.value = HomeEvent.Success
                }
                is Resource.Error -> {
                    _state.value = HomeState(
                        error = ""
                    )
                    _event.value = HomeEvent.Error
                }
            }
        }.launchIn(viewModelScope)
    }
}