package com.example.domain

import com.example.databasezemoga.entity.PostEntity


data class PostItem(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
    var isFavorite: Boolean,
    var isRead: Boolean
)

fun PostItem.toEntity() = PostEntity(
    id = id,
    userId = userId,
    title = title,
    body = body,
    isFavorite = isFavorite,
    isRead = isRead
)

fun PostEntity.toUI() = PostItem(
    id = id,
    userId = userId,
    title = title,
    body = body,
    isFavorite = isFavorite,
    isRead = isRead
)