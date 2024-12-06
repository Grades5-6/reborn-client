package com.example.client.component.all

data class JobField(
    val name: String
)

fun getJobFields(): List<JobField> = listOf(
    JobField("IT"),
    JobField("교육"),
    JobField("사무직"),
    JobField("판매직"),
    JobField("의료"),
    JobField("서비스"),
    JobField("건설"),
    JobField("제조업"),
    JobField("예술")
)