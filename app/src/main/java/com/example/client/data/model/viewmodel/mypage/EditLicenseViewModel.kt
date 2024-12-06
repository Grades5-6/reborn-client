package com.example.client.data.model.viewmodel.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.client.data.model.response.LicensesGetResponse
import com.example.client.data.repository.mypage.EditLicenseRepository
import com.example.client.domain.TestUserInfo
import kotlinx.coroutines.launch
import retrofit2.Response

class EditLicenseViewModel(private val repository: EditLicenseRepository) : ViewModel() {

    fun setUserLicenses(selectedLicenses: List<LicensesGetResponse>) {
        viewModelScope.launch {
            try {
                val response: Response<Void> = repository.setUserLicense(selectedLicenses)
                if (response.isSuccessful) {
                    println("Interest fields updated successfully!")
                    TestUserInfo.LICENSES.clear()
                    TestUserInfo.LICENSES.addAll(selectedLicenses.map { license ->
                        LicensesGetResponse(
                            jmfldnm = license.jmfldnm,
                            seriesnm = license.seriesnm,
                            expirationDate = license.expirationDate
                        )
                    })
                } else {
                    println("Failed to update interest fields: ${response.code()}")
                }
            } catch (e: Exception) {
                println("Error updating interest fields: ${e.message}")
            }
        }
    }
}