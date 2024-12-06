package com.example.client.data.model.viewmodel.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.client.data.repository.mypage.EditRegionRepository

class EditRegionViewModelFactory(private val repository: EditRegionRepository) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditRegionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditRegionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}