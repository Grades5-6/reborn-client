package com.example.client.data.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.client.data.model.response.CommunityGetResponse
import com.example.client.data.repository.CommunityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommunityViewModel(private val repository: CommunityRepository) : ViewModel() {
    private val _postState = MutableStateFlow<List<CommunityGetResponse>>(emptyList())
    val postState: StateFlow<List<CommunityGetResponse>> = _postState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun getPost() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            repository.getCommunity().fold(
                onSuccess = { post ->
                    _postState.value = post
                },
                onFailure = { throwable ->
                    _errorMessage.value = throwable.message ?: "알 수 없는 에러 발생"
                }
            )
            _isLoading.value = false
        }
    }
}