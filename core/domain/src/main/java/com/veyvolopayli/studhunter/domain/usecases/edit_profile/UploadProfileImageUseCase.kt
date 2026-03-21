package com.veyvolopayli.studhunter.domain.usecases.edit_profile

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.lang.Exception
import javax.inject.Inject

class UploadProfileImageUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val prefs: SharedPreferences
) {
    operator fun invoke(imageUri: String): Flow<String?> = flow {
        try {
            val token = prefs.getString(Constants.JWT, null) ?: run {
                emit(null)
                return@flow
            }
            val imageFile = File(imageUri)
            val requestBody = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val part = MultipartBody.Part.createFormData("avatar", imageFile.name, requestBody)
            val stringResponse = userRepository.uploadProfileImage(token, part)
            emit(stringResponse)
        } catch (e: Exception) {
            emit(null)
        }
    }
}