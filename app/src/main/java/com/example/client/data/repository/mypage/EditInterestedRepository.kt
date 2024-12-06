package com.example.client.data.repository.mypage

import com.example.client.data.api.ApiService
import com.example.client.data.model.request.mypage.EditInterestedRequest
import retrofit2.Response

class EditInterestedRepository(private val apiService: ApiService) {
    suspend fun setUserInterests(
        interestedField: List<String>
    ): Response<Void> {
        val request = EditInterestedRequest(interestedField)
        return apiService.setUserInterests(request)
    }
}