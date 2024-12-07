package com.example.client.data.model.response

data class CommunityGetResponse(
    val id : Int,
    val author : String,
    val title:String,
    val content:String,
    val likes_count : Int,
    val comments_count : Int
)
