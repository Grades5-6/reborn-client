package com.example.client.data.model.request

import com.example.client.data.model.response.LicensesGetResponse

data class JobOnBoardingRequest(
    val sex : String,
    val year: Int,
    val licenses : List<LicensesGetResponse>
)