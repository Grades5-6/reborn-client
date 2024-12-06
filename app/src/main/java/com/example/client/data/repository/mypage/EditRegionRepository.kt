package com.example.client.data.repository.mypage

import com.example.client.data.api.ApiService
import com.example.client.data.model.request.mypage.EditInterestedRequest
import com.example.client.data.model.request.mypage.EditRegionRequest
import retrofit2.Response

class EditRegionRepository(private val apiService: ApiService) {
    suspend fun setUserRegion(
        region: String
    ): Response<Void> {
        val request = EditRegionRequest(region)
        return apiService.setUserRegion(request)
    }
}