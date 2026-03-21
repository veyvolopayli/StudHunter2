package com.veyvolopayli.studhunter.domain.repository

import com.veyvolopayli.studhunter.domain.model.requests.UpdateRequest
import com.veyvolopayli.studhunter.domain.model.responses.CheckUpdateResponse
import com.veyvolopayli.studhunter.domain.model.responses.UpdateResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming
import java.io.File

interface UpdateRepository {

    suspend fun checkUpdate(version: String): CheckUpdateResponse

    suspend fun downloadUpdate(): ResponseBody

//    suspend fun installUpdate(file: File)

}