package com.veyvolopayli.studhunter.domain.usecases.update

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.veyvolopayli.studhunter.common.FileUtil.uriFromFile
import com.veyvolopayli.studhunter.presentation.update_app_screen.InstallUpdateResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

class InstallUpdateUseCase {
    operator fun invoke(updateAppFile: File, activity: FragmentActivity): Flow<InstallUpdateResult<String>> = flow {
        try {
            if (updateAppFile.exists()) {
                val context = activity.applicationContext
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(uriFromFile(context, updateAppFile), "application/vnd.android.package-archive")
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                context.startActivity(intent)
                activity.finish()
            } else {
                emit(InstallUpdateResult.FileNotFound())
            }
        } catch (e: Exception) {
            emit(InstallUpdateResult.Error("Some unexpected error."))
        }
    }
}