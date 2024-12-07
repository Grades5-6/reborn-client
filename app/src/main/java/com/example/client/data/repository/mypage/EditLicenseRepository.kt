package com.example.client.data.repository.mypage

import com.example.client.data.api.ApiService
import com.example.client.data.model.request.mypage.EditInterestedRequest
import com.example.client.data.model.request.mypage.EditLicenseRequest
import com.example.client.data.model.response.LicensesGetResponse
import retrofit2.Response

class EditLicenseRepository(private val apiService: ApiService) {
    suspend fun setUserLicense(
        licenses: List<LicensesGetResponse>
    ): Response<Void> {
        val request = EditLicenseRequest(licenses)
        return apiService.setUserLicenses(request)
    }
}