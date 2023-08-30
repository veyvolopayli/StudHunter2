package com.veyvolopayli.studhunter.domain.usecases.publication

import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class ImageUrlValidityUseCase @Inject constructor() {
    operator fun invoke(publicationId: String): Flow<Resource<List<String>>> = flow {
        val validImages = mutableListOf<String>()
        repeat(3) { n ->
            val imageURL = "${Constants.CLOUD_PUB_IMAGES_PATH}$publicationId/$n"
            try {
                val isImageValid = withContext(Dispatchers.IO) {
                    HttpURLConnection.setFollowRedirects(false)
                    val httpURLConnection = URL(imageURL).openConnection() as HttpURLConnection
                    httpURLConnection.requestMethod = "HEAD"
                    httpURLConnection.responseCode == HttpURLConnection.HTTP_OK
                }
                if (isImageValid) validImages.add(imageURL)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        emit(Resource.Success(validImages))
    }
}