package com.example.client.data.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.client.data.repository.JobPostRepository

class JobPostViewModelFactory(private val repository: JobPostRepository) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobPostViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JobPostViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}