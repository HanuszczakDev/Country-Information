package com.hanuszczak.countryinformation.viewmodel.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.hanuszczak.countryinformation.model.CountryDatabase
import com.hanuszczak.countryinformation.model.domain.Country
import com.hanuszczak.countryinformation.model.repository.ApiRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = CountryDatabase.getInstance(application)
    private val repository = ApiRepository(database)

    init {
        Log.d("MainViewModel", "init{}")
        viewModelScope.launch {
            try {
                repository.getCountriesFromApi()
            } catch (e: java.lang.Exception) {
                Log.e("MainViewModel", "exception in init block: ${e.localizedMessage}")
            }
        }
    }

    var countries: LiveData<List<Country>> = repository.countries
}