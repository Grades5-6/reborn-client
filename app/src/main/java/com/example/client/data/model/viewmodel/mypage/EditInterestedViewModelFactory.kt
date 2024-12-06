package com.example.client.data.model.viewmodel.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.client.data.repository.mypage.EditInterestedRepository

class EditInterestedViewModelFactory(private val repository: EditInterestedRepository) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditInterestedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditInterestedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}