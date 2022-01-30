package com.example.networking.usecase

import com.example.domain.PostResponse
import com.example.networking.datasource.GetPostsDataSource
import com.example.networking.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostInteractor@Inject constructor(private val dataSource: GetPostsDataSource) : GetPostsUseCase {
    override fun getPosts(): Flow<Result<List<PostResponse>>> = dataSource.getPosts()
}