package com.example.client.data.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.client.data.model.response.JobPostResponse
import com.example.client.data.repository.JobPostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class JobPostViewModel(private val repository: JobPostRepository) : ViewModel() {
    private val _jobList = MutableStateFlow<List<JobPostResponse>>(emptyList())
    val jobList = _jobList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun getJob() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            try {
                val response: Response<List<JobPostResponse>> = repository.getJobList()
                if (response.isSuccessful) {
                    _jobList.value = response.body() ?: emptyList()
                    println("Job list fetched successfully!")
                    println(_jobList.value)
                } else {
                    _error.value = "Failed to fetch jobs: ${response.code()}"
                    println("Failed to fetch job list: ${response.code()}")
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
                println("Error fetching job list: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}