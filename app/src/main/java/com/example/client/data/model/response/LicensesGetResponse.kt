package com.example.client.data.model.response

data class LicensesGetResponse(
    val jmfldnm: String,
    val seriesnm: String,
    val expirationDate: String ?= "2024-12-05",
)