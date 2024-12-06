package com.example.client.data.model.response

data class JobPostResponse(
    val jobTitle: String,
    val companyName: String,
    var hmUrl: String,
    val workAddr: String
)