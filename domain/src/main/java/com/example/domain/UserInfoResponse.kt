package com.example.domain

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("website") val website: String
)

fun UserInfoResponse.toUI() = UserInfo(
    id = id,
    name = name,
    email = email,
    phone = phone,
    website = website
)


