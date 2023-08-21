package com.veyvolopayli.studhunter.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val username: String,
    val rating: Double,
    val name: String,
    val surname: String?,
    val email: String,
    val university: String?
): Parcelable
