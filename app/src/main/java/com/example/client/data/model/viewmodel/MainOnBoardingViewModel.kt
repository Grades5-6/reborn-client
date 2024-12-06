package com.example.client.data.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.client.data.repository.MainOnBoardingRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainOnBoardingViewModel(private val repository: MainOnBoardingRepository) : ViewModel() {

    fun submitOnboarding(employmentStatus: String, region: String, interestedField: List<String>) {
        viewModelScope.launch {
            // API 호출
            val response: Response<Void> = repository.submitOnboarding(employmentStatus, region, interestedField)
            if (response.isSuccessful) {
                // 성공적으로 제출된 경우 처리
                println("Onboarding submitted successfully!")
            } else {
                // 실패한 경우 처리
                println("Failed to submit onboarding: ${response.code()}")
            }
        }
    }
}
