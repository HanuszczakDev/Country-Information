package com.hanuszczak.countryinformation.viewmodel.factory

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hanuszczak.countryinformation.viewmodel.main.MainViewModel

class ViewModelFactory(
    private val application: Application,
    private val savedStateHandle: SavedStateHandle
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application, savedStateHandle) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel")
    }
}