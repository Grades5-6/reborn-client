package com.example.client.data.repository

import com.example.client.data.api.ApiService
import com.example.client.data.model.response.CommunityGetResponse

class CommunityRepository(private val apiService: ApiService) {
    suspend fun getCommunity(): Result<List<CommunityGetResponse>> {
        return try {
            val response = apiService.getCommunity()
            if (response.isSuccessful) {
                val body = response.body() ?: emptyList()
                Result.success(body)
            } else {
                val errorMessage = response.errorBody()?.string() ?: "알수 없는 에러"
                Result.failure(Exception("Error : ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}