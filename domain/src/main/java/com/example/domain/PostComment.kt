package com.example.domain

import com.google.gson.annotations.SerializedName

data class PostComment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)
