package com.veyvolopayli.studhunter.presentation.edit_profile_screen

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EditProfileState(
    val name: String = "",
    val surname: String = "",
    val university: String = ""
): Parcelable
