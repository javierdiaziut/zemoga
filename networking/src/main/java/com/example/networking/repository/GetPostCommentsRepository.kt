package com.example.networking.repository

import com.example.domain.PostCommentsResponse
import com.example.networking.datasource.GetPostCommentsDataSource
import com.example.networking.di.ApiMethods
import com.example.networking.di.ApiMethodsFingerprint
import com.example.networking.utils.DispatcherProvider
import com.example.networking.utils.Result
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetPostCommentsRepository@Inject constructor(
    @ApiMethodsFingerprint val apiMethods: ApiMethods,
    private val dispatchers: DispatcherProvider
) : GetPostCommentsDataSource {
    override fun getPostComments(id : Int): Flow<Result<List<PostCommentsResponse>>> {
        return flow<Result<List<PostCommentsResponse>>> {

            val response = apiMethods.getPostComments(id = id)
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
