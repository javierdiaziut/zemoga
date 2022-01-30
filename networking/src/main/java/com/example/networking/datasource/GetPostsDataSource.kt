package com.example.networking.datasource

import com.example.domain.PostResponse
import com.example.networking.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetPostsDataSource {
    fun getPosts() : Flow<Result<List<PostResponse>>>
}