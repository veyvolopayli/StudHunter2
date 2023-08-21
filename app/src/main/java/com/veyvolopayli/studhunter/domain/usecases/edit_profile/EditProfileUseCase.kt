package com.veyvolopayli.studhunter.domain.usecases.edit_profile

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.model.requests.EditProfileRequest
import com.veyvolopayli.studhunter.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class EditProfileUseCase @Inject constructor(
    private val repository: UserRepository,
    private val prefs: SharedPreferences
) {
    operator fun invoke(name: String, surname: String, university: String) = flow<Resource<Boolean>> {
        try {
            val token = prefs.getString(Constants.JWT, null) ?: run {
                emit(Resource.Error(ErrorType.Unauthorized()))
                return@flow
            }
            if (name.isBlank() || surname.isBlank() || university.isBlank()) {
                emit(Resource.Success(true))
                return@flow
            }
            val editProfileRequest = EditProfileRequest(
                name = name,
                surname = surname,
                university = university
            )
            val edited = repository.editProfile(token, editProfileRequest)
            emit(Resource.Success(edited))
        } catch (e: HttpException) {
            emit(Resource.Error(ErrorType.NetworkError()))
        } catch (e: Exception) {
            emit(Resource.Error(ErrorType.ServerError()))
        }
    }
}