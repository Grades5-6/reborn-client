package com.example.client.data.repository

import com.example.client.data.api.ApiService
import com.example.client.data.model.response.MyPageResponse
import retrofit2.Response

class MyPageRepository(private val apiService: ApiService) {
    suspend fun getUser(): Response<MyPageResponse> {
        return apiService.getUser()
    }
}