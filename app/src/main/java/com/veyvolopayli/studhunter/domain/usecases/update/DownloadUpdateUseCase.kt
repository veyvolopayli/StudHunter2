package com.veyvolopayli.studhunter.domain.usecases.update

import android.content.Context
import android.os.Environment
import android.util.Log
import com.veyvolopayli.studhunter.BuildConfig
import com.veyvolopayli.studhunter.common.CheckUpdateResult
import com.veyvolopayli.studhunter.common.DownloadUpdateResult
import com.veyvolopayli.studhunter.domain.repository.UpdateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
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
            val downloadsDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            val updateAppFile = File(downloadsDir, "stud-hunter.apk")
            if (updateAppFile.exists()) updateAppFile.delete()
            withContext(Dispatchers.IO) {
                updateAppFile.createNewFile()
                val responseBody = updateRepository.downloadUpdate()
                val bytes = responseBody.bytes()

                if (updateAppFile.exists()) updateAppFile.writeBytes(bytes)
                else println("File doesn't exists")

            }
            emit(DownloadUpdateResult.Downloaded(updateAppFile))
        } catch (e: HttpException) {
            emit(DownloadUpdateResult.Error("HttpException"))
        } catch (e: IOException) {
            emit(DownloadUpdateResult.Error("IOException"))
        } catch (e: Exception) {
            println(e.localizedMessage)
            emit(DownloadUpdateResult.Error("Exception"))
        }

    }
}