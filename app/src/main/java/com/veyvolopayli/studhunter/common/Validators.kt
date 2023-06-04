package com.veyvolopayli.studhunter.common

import android.util.Patterns

private val alphabetEN = 'a'..'z'
private val alphabetRU = ('а'..'я') + 'ё'
private val numbers = 0..9

fun String.emailIsValid(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.passwordIsValid(): Boolean {
    if (this.length < 8) return false
    val lowercasePass = this.lowercase()
    return lowercasePass.all { !it.isWhitespace() && it !in alphabetRU }
}

fun String.usernameIsValid(): Boolean {
    if (this.length < 4) return false
    return this.lowercase().all { it in alphabetEN || it.digitToInt() in numbers }
}

fun String.nameOrSurnameIsValid(): Boolean =
    (this.length >= 2 && this.lowercase().all { it in alphabetRU || it in alphabetEN })