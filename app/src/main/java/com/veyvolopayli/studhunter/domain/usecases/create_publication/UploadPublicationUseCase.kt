package com.veyvolopayli.studhunter.domain.usecases.create_publication

import android.content.SharedPreferences
import android.net.Uri
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.PublicationToUpload
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import retrofit2.http.Multipart
import java.io.File
import javax.inject.Inject

class UploadPublicationUseCase @Inject constructor(
    private val publicationRepository: PublicationRepository,
    private val prefs: SharedPreferences
) {
    operator fun invoke(images: List<String>, publicationToUpload: PublicationToUpload): Flow<Resource<String>> = flow {
        try {
            val parts = images.map { imageUri ->
                val imageFile = File(imageUri)
                val requestBody = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())

                MultipartBody.Part.createFormData("images", imageFile.name, requestBody)
            }

            val token = prefs.getString(Constants.JWT, null) ?: run {
                emit(Resource.Error(ErrorType.Unauthorized()))
                return@flow
            }

            val publicationId = publicationRepository.uploadPublication(parts, publicationToUpload, token)
            emit(Resource.Success(publicationId))
        } catch (e: HttpException) {
            emit(Resource.Error(ErrorType.NetworkError()))
            return@flow
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(ErrorType.LocalError()))
        }
    }
}