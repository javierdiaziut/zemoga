package com.example.networking.usecase

import com.example.domain.PostResponse
import com.example.networking.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetPostsUseCase {
    fun getPosts() : Flow<Result<List<PostResponse>>>
}