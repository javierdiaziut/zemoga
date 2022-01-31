package com.example.networking.datasource

import com.example.domain.UserInfoResponse
import com.example.networking.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetUserInfoDataSource {
    fun getUserInfo(id : Int) : Flow<Result<UserInfoResponse>>
}