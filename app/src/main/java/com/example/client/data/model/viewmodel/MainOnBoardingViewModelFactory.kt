package com.example.client.data.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.client.data.repository.MainOnBoardingRepository

class MainOnBoardingViewModelFactory(private val repository: MainOnBoardingRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainOnBoardingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainOnBoardingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
