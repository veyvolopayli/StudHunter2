package com.veyvolopayli.studhunter.domain.repository

interface PrefsRepository {
    fun getJwtToken(): String?
    fun insertJwtToken(token: String)
    fun clearPrefs()
}