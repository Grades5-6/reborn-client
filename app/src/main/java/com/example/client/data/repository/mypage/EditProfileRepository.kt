package com.example.client.data.repository.mypage

import com.example.client.data.api.ApiService
import com.example.client.data.model.request.mypage.EditProfileRequest
import retrofit2.Response

class EditProfileRepository(private val apiService: ApiService) {
    suspend fun setUserProfile(
        nickName: String,
        profileImg: String,
        employmentStatus: String
    ): Response<Void> {
        val request = EditProfileRequest(nickName, profileImg,employmentStatus)
        return apiService.setUserProfile(request)
    }
}