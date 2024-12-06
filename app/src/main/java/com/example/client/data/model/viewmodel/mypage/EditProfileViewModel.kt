package com.example.client.data.model.viewmodel.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.client.data.repository.mypage.EditProfileRepository
import com.example.client.domain.TestUserInfo
import kotlinx.coroutines.launch
import retrofit2.Response

class EditProfileViewModel(private val repository: EditProfileRepository) : ViewModel() {
    fun setUserProfile(nickName: String, profileImg: String, employmentStatus: String){
        viewModelScope.launch {
            try {
                val response: Response<Void> = repository.setUserProfile(nickName,profileImg, employmentStatus)
                if (response.isSuccessful) {
                    println("profile updated successfully!")
                    TestUserInfo.USERIMG = profileImg
                    TestUserInfo.TEST_USERNAME = nickName
                    TestUserInfo.EMPLOYMENT = employmentStatus
                } else {
                    println("Failed to update profile fields: ${response.code()}")
                }
            } catch (e: Exception) {
                println("Error updating profile fields: ${e.message}")
            }

        }
    }
}
