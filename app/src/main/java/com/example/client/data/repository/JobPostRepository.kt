package com.example.client.data.repository

import androidx.compose.runtime.key
import com.example.client.data.api.ApiService
import com.example.client.data.model.response.JobPostResponse
import retrofit2.Response

class JobPostRepository(private val apiService: ApiService) {
    suspend fun getJobList(): Response<List<JobPostResponse>> {
        return apiService.getJob()
    }
    suspend fun searchJob(keyword:String):Response<List<JobPostResponse>>{
        return apiService.searchJob(keyword = keyword)
    }
}