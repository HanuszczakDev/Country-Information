package com.hanuszczak.countryinformation.model.domain

data class Country(
    val nameOfficial: String,
    val nameCommon: String,
    val currencyName: String,
    val currencySymbol: String,
    val region: String,
    val subregion: String,
    val latitude: Double,
    val altitude: Double,
    val area: Double,
    val flagUrl: String
)