package com.example.client.data.model.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.client.data.model.response.LicensesGetResponse
import com.example.client.data.repository.JobOnBoardingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.Response

class JobOnBoardingViewModel(private val repository: JobOnBoardingRepository) : ViewModel() {
    private val _licensesState = MutableStateFlow<List<LicensesGetResponse>>(emptyList())
    val licensesState: StateFlow<List<LicensesGetResponse>> = _licensesState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _selectedLicenses = MutableStateFlow<List<LicensesGetResponse>>(emptyList())
    val selectedLicenses: StateFlow<List<LicensesGetResponse>> = _selectedLicenses

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

    fun toggleLicencesSelection(license: LicensesGetResponse) {
        _selectedLicenses.value = if (_selectedLicenses.value.contains(license)) {
            _selectedLicenses.value - license
        } else {
            _selectedLicenses.value + license
        }
    }

    fun isLicenseSelectedFlow(license: LicensesGetResponse): StateFlow<Boolean> {
        return _selectedLicenses.map { it.contains(license) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = false
        )
    }

    fun submitJobOnboarding(sex: String, year: Int) {
        viewModelScope.launch {
            val response: Response<Void> =
                repository.submitJobOnboarding(sex, year, _selectedLicenses.value)
            if (response.isSuccessful) {
                println("Job Onboarding submitted successfully!")
            } else {
                println("Failed to submit Job onboarding: ${response.code()} ${response.message()}")
            }
        }
    }

}