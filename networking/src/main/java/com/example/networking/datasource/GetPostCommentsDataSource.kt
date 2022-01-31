package com.example.networking.datasource

import com.example.domain.PostCommentsResponse
import com.example.networking.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetPostCommentsDataSource {
    fun getPostComments(id : Int) : Flow<Result<List<PostCommentsResponse>>>
}