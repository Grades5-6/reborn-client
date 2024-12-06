package com.example.client.data.model.response

data class MyPageResponse(
    val nickName: String,
    val profileImg: String,
    val rebornTemperature: Int,
    val employmentStatus: String,
    val region: String,
    val interestedField: List<String>,
    val licenses: List<LicenseResponse>
)