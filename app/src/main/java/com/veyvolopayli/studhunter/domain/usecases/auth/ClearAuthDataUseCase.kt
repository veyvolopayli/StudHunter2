package com.veyvolopayli.studhunter.domain.usecases.auth

import com.veyvolopayli.studhunter.domain.repository.PrefsRepository
import javax.inject.Inject

class ClearAuthDataUseCase @Inject constructor(
    private val prefsRepository: PrefsRepository
) {
    operator fun invoke() {
        prefsRepository.clearPrefs()
    }
}