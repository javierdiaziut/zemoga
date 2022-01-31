package com.example.networking.usecase

import com.example.domain.PostCommentsResponse
import com.example.domain.PostResponse
import com.example.networking.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetPostCommentsUseCase {
    fun getPostComments(id : Int) : Flow<Result<List<PostCommentsResponse>>>
}