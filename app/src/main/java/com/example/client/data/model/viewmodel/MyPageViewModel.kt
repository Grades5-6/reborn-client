package com.example.client.data.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.client.data.model.response.MyPageResponse
import com.example.client.data.repository.MyPageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class MyPageViewModel(private val repository: MyPageRepository) : ViewModel() {
    private val _user = MutableStateFlow<MyPageResponse?>(null)
    val user: StateFlow<MyPageResponse?> = _user.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun getUser() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response: Response<MyPageResponse> = repository.getUser()
                if (response.isSuccessful) {
                    _user.value = response.body()
                    println("User info fetched successfully!")
                    println(_user.value)
                } else {
                    _error.value = "Failed to fetch user info: ${response.code()}"
                    println("Failed to fetch user info: ${response.code()}")
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
                println("Error fetching user info: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}