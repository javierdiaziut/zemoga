package com.example.networking.usecase

import com.example.domain.UserInfoResponse
import com.example.networking.datasource.GetUserInfoDataSource
import com.example.networking.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInfoInteractor @Inject constructor(private val dataSource: GetUserInfoDataSource) :
    GetUserInfoUseCase {
    override fun getUserInfo(id: Int): Flow<Result<UserInfoResponse>> = dataSource.getUserInfo(id)
}