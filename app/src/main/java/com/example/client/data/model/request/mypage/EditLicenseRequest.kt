package com.example.client.data.model.request.mypage

import com.example.client.data.model.response.LicensesGetResponse

data class EditLicenseRequest (
    val licenses: List<LicensesGetResponse>
)