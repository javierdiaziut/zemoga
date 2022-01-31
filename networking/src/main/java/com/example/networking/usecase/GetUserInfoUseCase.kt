package com.example.networking.usecase

import com.example.domain.UserInfoResponse
import com.example.networking.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetUserInfoUseCase {
    fun getUserInfo(id : Int) : Flow<Result<UserInfoResponse>>
}