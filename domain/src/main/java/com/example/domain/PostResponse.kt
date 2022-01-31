package com.example.domain

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String)


fun PostResponse.toUI() = PostItem(
     id = id,
     userId = userId,
     title = title,
     body = body,
     isFavorite = false,
     isRead = true
)