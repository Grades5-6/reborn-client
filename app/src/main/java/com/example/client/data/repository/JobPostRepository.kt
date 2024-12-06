package com.example.client.data.repository

import com.example.client.data.api.ApiService
import com.example.client.data.model.response.JobPostResponse
import retrofit2.Response

class JobPostRepository(private val apiService: ApiService) {
    suspend fun getJobList(): Response<List<JobPostResponse>> {
        return apiService.getJob()
    }
}