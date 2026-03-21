package com.veyvolopayli.studhunter.data.repository

import com.veyvolopayli.studhunter.data.remote.StudHunterApi
import com.veyvolopayli.studhunter.domain.model.responses.CheckUpdateResponse
import com.veyvolopayli.studhunter.domain.repository.UpdateRepository
import okhttp3.ResponseBody

class UpdateRepositoryImpl(private val api: StudHunterApi) : UpdateRepository {

    override suspend fun checkUpdate(version: String): CheckUpdateResponse {
        return api.checkUpdate(version)
    }

    override suspend fun downloadUpdate(): ResponseBody {
        return api.downloadUpdate()
    }

}