package com.hanuszczak.countryinformation.viewmodel.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.hanuszczak.countryinformation.model.CountryDatabase
import com.hanuszczak.countryinformation.model.domain.Country
import com.hanuszczak.countryinformation.model.repository.ApiRepository
import kotlinx.coroutines.launch

const val SCROLL_POSITION_KEY = "rv.scroll_position"

class MainViewModel(application: Application, private val savedStateHandle: SavedStateHandle) : AndroidViewModel(application) {
    private val database = CountryDatabase.getInstance(application)
    private val repository = ApiRepository(database)

    private val _navigateToCountry = MutableLiveData<Country?>()
    val navigateToCountry: LiveData<Country?>
        get() = _navigateToCountry

    var scrollPosition: Int = savedStateHandle[SCROLL_POSITION_KEY] ?: 0
        private set

    // Variable for detecting orientation change
    var didOrientationChange = false

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

    fun onCountryClicked(country: Country) {
        _navigateToCountry.value = country
    }

    fun onCountryNavigated() {
        _navigateToCountry.value = null
    }

    fun setScrollPosition(newPosition: Int) {
        if (!didOrientationChange) {
            scrollPosition = newPosition
            savedStateHandle[SCROLL_POSITION_KEY] = newPosition
        }
    }
}