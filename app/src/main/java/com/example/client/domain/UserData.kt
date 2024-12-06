package com.example.client.domain

data class User(
    val userId: Long,
    val nickname: String?,
    val profileImageUrl: String? // 필요에 따라 추가
)
