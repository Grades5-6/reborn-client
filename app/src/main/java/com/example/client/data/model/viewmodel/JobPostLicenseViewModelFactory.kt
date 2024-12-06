package com.example.client.data.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.client.data.repository.JobPostLicenseRepository

class JobPostLicenseViewModelFactory(private val repository: JobPostLicenseRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobPostLicenseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JobPostLicenseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}