package com.hanuszczak.countryinformation.viewmodel.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanuszczak.countryinformation.model.dto.CountryDto
import com.hanuszczak.countryinformation.model.repository.CountryApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _countryDto = MutableLiveData<CountryDto>()
    val countryDto: LiveData<CountryDto>
        get() = _countryDto

    init {
        Log.d("MainViewModel", "init{}")
        viewModelScope.launch {
            try {
                val countryDtoList = CountryApi.retrofitService.getAll()
                _countryDto.value = countryDtoList[0]
                Log.d("MainViewModel", "response: ${countryDtoList[0]}")
            } catch (e: java.lang.Exception) {
                Log.e("MainViewModel", "exception in init block: ${e.localizedMessage}")
            }
        }
    }
}