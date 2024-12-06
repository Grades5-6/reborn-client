package com.example.client.data.model.viewmodel.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.client.component.all.JobField
import com.example.client.data.repository.mypage.EditInterestedRepository
import com.example.client.data.repository.mypage.EditRegionRepository
import com.example.client.domain.TestUserInfo
import kotlinx.coroutines.launch
import retrofit2.Response

class EditRegionViewModel(private val repository: EditRegionRepository) : ViewModel() {

    fun setUserRegion(region: String) {
        viewModelScope.launch {
            try {
                val response: Response<Void> = repository.setUserRegion(region)
                if (response.isSuccessful) {
                    println("region updated successfully!")
                    TestUserInfo.REGION = region
                } else {
                    println("Failed to update region fields: ${response.code()}")
                }
            } catch (e: Exception) {
                println("Error updating region fields: ${e.message}")
            }
        }
    }
}