package com.veyvolopayli.studhunter.common

sealed class DataUniquenessResult {
    data object Unique : DataUniquenessResult()
    data object NotUnique : DataUniquenessResult()
    data object Error : DataUniquenessResult()
}
