package com.veyvolopayli.studhunter.domain.usecases.categories

import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.domain.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: PublicationRepository
) {
    operator fun invoke(): Flow<Resource<Map<Int, String>>> = flow {
        try {
            val categories = repository.getCategories()
            emit(Resource.Success(categories))
        } catch (e: HttpException) {
            emit(Resource.Error(ErrorType.ServerError()))
        } catch (e: Exception) {
            emit(Resource.Error(ErrorType.LocalError()))
        }
    }
}