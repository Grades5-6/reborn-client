package com.example.client.data.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.client.data.model.response.JobPostResponse
import com.example.client.data.repository.JobPostLicenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class JobPostLicenseViewModel(private val repository: JobPostLicenseRepository): ViewModel() {
    private val _jobList = MutableStateFlow<List<JobPostResponse>>(emptyList())
    val jobList = _jobList.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()
    fun getJobLicense(jmfldnm: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response: Response<List<JobPostResponse>> = repository.getJobLicenseList(jmfldnm)
                if (response.isSuccessful) {
                    _jobList.value = response.body() ?: emptyList()
                    println("Job license list fetched successfully!")
                    println(_jobList.value)
                } else {
                    _error.value = "Failed to fetch jobs: ${response.code()}"
                    println("Failed to fetch job license list: ${response.code()}")
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
                println("Error fetching job license list: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}