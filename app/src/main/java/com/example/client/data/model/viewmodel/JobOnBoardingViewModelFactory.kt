package com.example.client.data.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.client.data.repository.JobOnBoardingRepository
import com.example.client.data.repository.JobPostRepository

class JobOnBoardingViewModelFactory(private val repository: JobOnBoardingRepository) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobOnBoardingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JobOnBoardingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}