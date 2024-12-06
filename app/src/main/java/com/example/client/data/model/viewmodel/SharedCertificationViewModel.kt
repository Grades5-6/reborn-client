package com.example.client.data.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.client.data.model.response.LicensesGetResponse
import com.example.client.data.repository.JobOnBoardingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedCertificationViewModel (
    private val repository: JobOnBoardingRepository
) : ViewModel() {
    private val _licensesState = MutableStateFlow<List<LicensesGetResponse>>(emptyList())
    val licensesState: StateFlow<List<LicensesGetResponse>> = _licensesState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchLicenses() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            repository.getLicenses().fold(
                onSuccess = { licenses ->
                    _licensesState.value = licenses
                },
                onFailure = { throwable ->
                    _errorMessage.value = throwable.message ?: "알 수 없는 에러 발생"
                }
            )
            _isLoading.value = false
        }
    }

}