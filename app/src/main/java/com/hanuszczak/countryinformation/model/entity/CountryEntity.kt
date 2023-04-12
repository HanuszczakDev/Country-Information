package com.hanuszczak.countryinformation.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hanuszczak.countryinformation.model.domain.Country

@Entity(tableName = "country")
data class CountryEntity(
    @PrimaryKey
    @ColumnInfo(name = "name_official")
    var nameOfficial: String,
    @ColumnInfo(name = "name_common")
    var nameCommon: String,
    @ColumnInfo(name = "currency_name")
    var currencyName: String,
    @ColumnInfo(name = "currency_symbol")
    var currencySymbol: String,
    @ColumnInfo(name = "region")
    var region: String,
    @ColumnInfo(name = "subregion")
    var subregion: String,
    @ColumnInfo(name = "latitude")
    var latitude: Double,
    @ColumnInfo(name = "altitude")
    var altitude: Double,
    @ColumnInfo(name = "area")
    var area: Double,
    @ColumnInfo(name = "flag_url")
    var flagUrl: String
)

fun List<CountryEntity>.asDomainModel(): List<Country> {
    return map {
        Country(
            nameOfficial = it.nameOfficial,
            nameCommon = it.nameCommon,
            currencyName = it.currencyName,
            currencySymbol = it.currencySymbol,
            region = it.region,
            subregion = it.subregion,
            latitude = it.latitude,
            altitude = it.altitude,
            area = it.area,
            flagUrl = it.flagUrl
        )
    }
}