package com.example.client.data.model.viewmodel.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.client.component.all.JobField
import com.example.client.data.repository.mypage.EditInterestedRepository
import com.example.client.domain.TestUserInfo
import kotlinx.coroutines.launch
import retrofit2.Response

class EditInterestedViewModel(private val repository: EditInterestedRepository) : ViewModel() {

    fun setUserInterests(selectedFields: List<JobField>) {
        viewModelScope.launch {
            try {
                // JobField 리스트를 String 리스트로 변환
                val fieldNames = selectedFields.map { it.name }

                // API 호출
                val response: Response<Void> = repository.setUserInterests(fieldNames)
                if (response.isSuccessful) {
                    println("Interest fields updated successfully!")
                    TestUserInfo.INTEREST = fieldNames
                } else {
                    println("Failed to update interest fields: ${response.code()}")
                }
            } catch (e: Exception) {
                println("Error updating interest fields: ${e.message}")
            }
        }
    }
}