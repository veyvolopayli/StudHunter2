package com.veyvolopayli.studhunter.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilterRequest(
    val minPrice: Int? = null,
    val maxPrice: Int? = null,
    val priceTypes: List<String>? = null,
    val districts: List<String>? = null,
    val categories: List<String>? = null,
    val minUserRating: Int? = null
) : Parcelable
