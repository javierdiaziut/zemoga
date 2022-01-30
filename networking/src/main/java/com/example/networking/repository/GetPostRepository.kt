package com.example.networking.repository

import com.example.domain.PostResponse
import com.example.networking.datasource.GetPostsDataSource
import com.example.networking.di.ApiMethods
import com.example.networking.di.ApiMethodsFingerprint
import com.example.networking.utils.DispatcherProvider
import com.example.networking.utils.Result
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetPostRepository@Inject constructor(
    @ApiMethodsFingerprint val apiMethods: ApiMethods,
    private val dispatchers: DispatcherProvider
) : GetPostsDataSource {
    override fun getPosts(): Flow<Result<List<PostResponse>>> {
        return flow<Result<List<PostResponse>>> {

            val response = apiMethods.getPosts()
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()!!))
            } else {
                emit(Result.Failure(response.code().toString()))
            }

        }.onStart{
            emit(Result.Loading(null))
        }.catch {
            emit(Result.Failure(it.message?:""))
        }.flowOn(dispatchers.io())
    }
}
