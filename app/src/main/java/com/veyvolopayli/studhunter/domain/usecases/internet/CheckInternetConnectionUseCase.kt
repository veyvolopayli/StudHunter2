package com.veyvolopayli.studhunter.domain.usecases.internet

import android.content.Context
import com.veyvolopayli.studhunter.common.NetworkUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckInternetConnectionUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke() = NetworkUtil.isNetworkAvailable(context)
}