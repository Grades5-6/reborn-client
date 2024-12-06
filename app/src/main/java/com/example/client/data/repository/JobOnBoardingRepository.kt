package com.example.client.data.repository

import com.example.client.data.api.ApiService
import com.example.client.data.model.request.JobOnBoardingRequest
import com.example.client.data.model.response.LicensesGetResponse
import retrofit2.Response

class JobOnBoardingRepository(private val apiService: ApiService) {
    suspend fun getLicenses(): Result<List<LicensesGetResponse>> {
        return try {
            val response = apiService.getLicenses()
            if (response.isSuccessful) {
                val body = response.body() ?: emptyList()
                Result.success(body)
            } else {
                val errorMessage = response.errorBody()?.string() ?: "알수 없는 에러"
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun submitJobOnboarding(
        sex: String,
        year: Int,
        licenses: List<LicensesGetResponse>
    ): Response<Void> {
        val request = JobOnBoardingRequest(sex, year, licenses)
        return apiService.submitJobOnboarding(request)
    }
}