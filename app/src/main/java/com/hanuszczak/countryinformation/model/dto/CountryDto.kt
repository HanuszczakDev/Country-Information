package com.hanuszczak.countryinformation.model.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryDto(
    val name: Name,
    val currencies: Map<String, Currency>?,
    val capital: List<String>?,
    val region: String,
    val subregion: String?,
    val latlng: List<Double>,
    val area: Double,
    val flags: Flag
)
@JsonClass(generateAdapter = true)
data class Name(
    val official: String,
    val common: String
)

@JsonClass(generateAdapter = true)
data class Currency(
    val name: String,
    val symbol: String?
)

@JsonClass(generateAdapter = true)
data class Flag(
    val png: String,
)
