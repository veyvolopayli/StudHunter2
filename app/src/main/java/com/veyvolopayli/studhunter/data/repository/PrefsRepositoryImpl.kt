package com.veyvolopayli.studhunter.data.repository

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.domain.repository.PrefsRepository
import javax.inject.Inject

class PrefsRepositoryImpl @Inject constructor(
    private val prefs: SharedPreferences
) : PrefsRepository {

    override fun getJwtToken(): String? {
        return prefs.getString(Constants.JWT, null)
    }

    override fun insertJwtToken(token: String) {
        prefs.edit().putString(Constants.JWT, token).apply()
    }
}