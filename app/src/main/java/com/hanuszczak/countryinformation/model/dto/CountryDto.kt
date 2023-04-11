package com.hanuszczak.countryinformation.model.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryDto(
    val official: String,
)