package com.veyvolopayli.studhunter.presentation.update_app_screen

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.studhunter.common.DownloadUpdateResult
import com.veyvolopayli.studhunter.common.FileUtil
import com.veyvolopayli.studhunter.domain.usecases.update.DownloadUpdateUseCase
import com.veyvolopayli.studhunter.domain.usecases.update.InstallUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.File
import javax.inject.Inject

@HiltViewModel
class UpdateAppViewModel @Inject constructor(
    private val downloadUpdateUseCase: DownloadUpdateUseCase,
//    private val installUpdateUseCase: InstallUpdateUseCase
) : ViewModel() {

    private val _state = MutableLiveData(UpdateAppState())
    val state: LiveData<UpdateAppState> = _state

    private val _event = MutableLiveData<DownloadUpdateResult<Unit>>()
    val event: LiveData<DownloadUpdateResult<Unit>> = _event

    fun downloadUpdate(context: Context, activity: FragmentActivity) {
        downloadUpdateUseCase(context).onEach { downloadUpdateResult ->
            when (downloadUpdateResult) {
                is DownloadUpdateResult.Downloading -> {
                    _state.value = UpdateAppState(buttonPressed = true, downloading = true)
                    _event.value = DownloadUpdateResult.Downloading()
                }
                is DownloadUpdateResult.Downloaded -> {
                    _state.value = UpdateAppState(
                        buttonPressed = true,
                        downloading = false,
                        downloaded = false,
                        updateAppFile = downloadUpdateResult.downloadedUpdate
                    )
                    _event.value = DownloadUpdateResult.Downloaded()
                    installUpdate(downloadUpdateResult.downloadedUpdate!!, activity)
                }
                is DownloadUpdateResult.Error -> {
                    _state.value = UpdateAppState(error = "Some unexpected error.")
                    _event.value = DownloadUpdateResult.Error(downloadUpdateResult.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun installUpdate(updateAppFile: File, activity: FragmentActivity) {

        if (updateAppFile.exists()) {
            val context = activity.applicationContext
            val intent = Intent(Intent.ACTION_VIEW)
            intent.apply {
                setDataAndType(
                    FileUtil.uriFromFile(context, updateAppFile),
                    "application/vnd.android.package-archive"
                )
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            context.startActivity(intent)
            activity.finish()
        } else {
            _state.value = UpdateAppState(error = "Local update file doesn't exist.")
        }

        /*installUpdateUseCase(file, activity).onEach {

        }.launchIn(viewModelScope)*/
    }

}