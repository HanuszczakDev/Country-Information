package com.hanuszczak.countryinformation.model.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryDto(
    val name: Name,
    val currencies: Map<String, Currency>?
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

//@JsonClass(generateAdapter = true)
//data class Currencies(
//    val currencies: Map<String, Currency>? = null,
//)
