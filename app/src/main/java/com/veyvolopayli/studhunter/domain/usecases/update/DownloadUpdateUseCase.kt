package com.veyvolopayli.studhunter.domain.usecases.update

import android.content.Context
import android.os.Environment
import com.veyvolopayli.studhunter.BuildConfig
import com.veyvolopayli.studhunter.common.CheckUpdateResult
import com.veyvolopayli.studhunter.common.DownloadUpdateResult
import com.veyvolopayli.studhunter.domain.repository.UpdateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import javax.inject.Inject

class DownloadUpdateUseCase @Inject constructor(
    private val updateRepository: UpdateRepository
) {
    operator fun invoke(context: Context): Flow<DownloadUpdateResult<File>> = flow {

        try {
            emit(DownloadUpdateResult.Downloading())

            val responseBody = updateRepository.downloadUpdate()
            val bytes = responseBody.bytes()
            val downloadsDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            val updateAppFile = File(downloadsDir, "stud-hunter.apk")
            if (updateAppFile.exists()) updateAppFile.writeBytes(bytes)

            emit(DownloadUpdateResult.Downloaded(updateAppFile))
        } catch (e: HttpException) {
            emit(DownloadUpdateResult.Error())
        } catch (e: IOException) {
            emit(DownloadUpdateResult.Error())
        } catch (e: Exception) {
            emit(DownloadUpdateResult.Error())
        }

    }
}