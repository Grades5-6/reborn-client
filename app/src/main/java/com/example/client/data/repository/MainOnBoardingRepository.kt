package com.example.client.data.repository

import com.example.client.data.api.ApiService
import com.example.client.data.model.request.MainOnBoardingRequest
import retrofit2.Response

class MainOnBoardingRepository(private val apiService: ApiService) {
    // 온보딩 데이터를 제출하는 메서드
    suspend fun submitOnboarding(
        employmentStatus: String,
        region: String,
        interestedField: List<String>
    ): Response<Void> {
        // OnboardingRequest 객체 생성
        val request = MainOnBoardingRequest(employmentStatus, region, interestedField)
        // API 호출
        return apiService.submitOnboarding(request)
    }
}