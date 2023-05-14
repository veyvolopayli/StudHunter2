package com.veyvolopayli.studhunter.domain.usecases.update

import com.veyvolopayli.studhunter.BuildConfig
import com.veyvolopayli.studhunter.common.CheckUpdateResult
import com.veyvolopayli.studhunter.domain.repository.UpdateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class CheckUpdateUseCase @Inject constructor(
    private val updateRepository: UpdateRepository
) {

    operator fun invoke(): Flow<CheckUpdateResult<Boolean>> = flow {
        try {
            val currentAppVersion = BuildConfig.VERSION_NAME
            val curVerIntArr = currentAppVersion.split(".").map { it.toInt() }
            val newVersion = "${curVerIntArr[0]}.${curVerIntArr[1]}.${curVerIntArr[2] + 1}"

            val isUpdateExists = updateRepository.checkUpdate(newVersion).exists

            if (isUpdateExists) emit(CheckUpdateResult.UpdateAvailable())
            else emit(CheckUpdateResult.LastVersionInstalled())

        } catch (e: Exception) {
            emit(CheckUpdateResult.Error())
        }
    }

}