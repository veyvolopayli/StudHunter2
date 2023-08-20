package com.veyvolopayli.studhunter.common

import android.view.View
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun View.hide() { this.visibility = View.GONE }
fun View.show() { this.visibility = View.VISIBLE }

fun Long.millsToTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(date)
}

fun Long.millsToDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
    return format.format(date)
}