package com.example.client.data.model.request

data class MainOnBoardingRequest(
    val employmentStatus: String,
    val region: String,
    val interestedField: List<String>,
)