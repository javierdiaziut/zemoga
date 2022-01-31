package com.example.networking.usecase

import com.example.domain.PostCommentsResponse
import com.example.networking.datasource.GetPostCommentsDataSource
import com.example.networking.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostCommentsInteractor@Inject constructor(private val dataSource: GetPostCommentsDataSource) : GetPostCommentsUseCase {
    override fun getPostComments(id: Int): Flow<Result<List<PostCommentsResponse>>> = dataSource.getPostComments(id)
}