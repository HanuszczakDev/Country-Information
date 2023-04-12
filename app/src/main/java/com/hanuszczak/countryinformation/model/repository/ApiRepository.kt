package com.hanuszczak.countryinformation.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.hanuszczak.countryinformation.model.CountryDatabase
import com.hanuszczak.countryinformation.model.domain.Country
import com.hanuszczak.countryinformation.model.dto.countriesAsDatabaseModel
import com.hanuszczak.countryinformation.model.entity.CountryEntity
import com.hanuszczak.countryinformation.model.entity.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiRepository (private val database: CountryDatabase) {

    val countries: LiveData<List<Country>> = Transformations
        .map(database.countryDao.getAll()) {
            it.asDomainModel()
        }

    suspend fun getCountriesFromApi() {
        withContext(Dispatchers.IO) {
            try {
                Log.d("ApiRepository", "start")

                val response = CountryApi.retrofitService.getAll()
                Log.d("ApiRepository", "response: $response")

                val countriesArray: Array<CountryEntity> = countriesAsDatabaseModel(response)
                Log.d("ApiRepository", "countriesArray: $countriesArray")

                Log.d("ApiRepository", "Before dao")
                database.countryDao.insertAll(countriesAsDatabaseModel(response))
                Log.d("ApiRepository", "After dao")
            } catch (e: Exception) {
                e.localizedMessage?.let { Log.e("ApiRepository", it) }
            }
        }
    }
}