package com.example.client.data.repository

import com.example.client.data.api.ApiService
import com.example.client.data.model.request.JobPostLicenseRequest
import com.example.client.data.model.response.JobPostResponse
import retrofit2.Response

class JobPostLicenseRepository(private val apiService: ApiService) {
    suspend fun getJobLicenseList(
        jmfldname: String
    ): Response<List<JobPostResponse>>{
        val request = JobPostLicenseRequest(jmfldname)
        return apiService.getJobLicense(request)
    }
}