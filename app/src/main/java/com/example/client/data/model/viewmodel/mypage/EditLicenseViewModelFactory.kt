package com.example.client.data.model.viewmodel.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.client.data.repository.mypage.EditLicenseRepository

class EditLicenseViewModelFactory(private val repository: EditLicenseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditLicenseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditLicenseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}