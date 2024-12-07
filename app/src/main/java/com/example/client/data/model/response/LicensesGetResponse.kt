package com.example.client.data.model.response

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class LicensesGetResponse(
    val jmfldnm: String,
    val seriesnm: String,
    val expirationDate: String,
)