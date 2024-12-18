package com.example.client.data.model.viewmodel.mypage

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.client.data.model.response.LicensesGetResponse
import com.example.client.data.repository.mypage.EditLicenseRepository
import com.example.client.domain.TestUserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.Response

class EditLicenseViewModel(private val repository: EditLicenseRepository) : ViewModel() {
    private val _selectedLicenses = MutableStateFlow<List<LicensesGetResponse>>(emptyList())
    val selectedLicenses: StateFlow<List<LicensesGetResponse>> = _selectedLicenses

    fun isLicenseSelectedFlow(license: LicensesGetResponse): StateFlow<Boolean> {
        return _selectedLicenses.map { it.contains(license) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = false
        )
    }

    fun toggleLicencesSelection(license: LicensesGetResponse) {
        val currentList = _selectedLicenses.value.toMutableList()
        if (currentList.contains(license)) {
            // 이미 선택된 자격증이면 제거
            currentList.remove(license)
        } else {
            // 새로운 자격증이면 추가
            currentList.add(license)
        }
        _selectedLicenses.value = currentList
    }

    fun setUserLicenses(selectedLicenses: List<LicensesGetResponse>) {
        viewModelScope.launch {
            try {
                val response: Response<Void> = repository.setUserLicense(selectedLicenses)
                if (response.isSuccessful) {
                    println("license fields updated successfully!")
                    TestUserInfo.LICENSES.clear()
                    TestUserInfo.LICENSES.addAll(selectedLicenses.map { license ->
                        LicensesGetResponse(
                            jmfldnm = license.jmfldnm,
                            seriesnm = license.seriesnm,
                            expirationDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
                        )
                    })
                } else {
                    println("Failed to update license fields: ${response.code()}")
                }
            } catch (e: Exception) {
                println("Error updating license fields: ${e.message}")
            }
        }
    }
}