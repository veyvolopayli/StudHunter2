package com.veyvolopayli.studhunter.presentation.authorization.sign_up_screen

sealed class DataUniquenessResult {
    object Unique: DataUniquenessResult()
    object NotUnique: DataUniquenessResult()
    object Error: DataUniquenessResult()
}