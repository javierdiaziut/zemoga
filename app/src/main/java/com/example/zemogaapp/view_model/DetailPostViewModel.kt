package com.example.zemogaapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.PostCommentsResponse
import com.example.domain.UserInfoResponse
import com.example.networking.usecase.GetPostCommentsUseCase
import com.example.networking.usecase.GetUserInfoUseCase
import com.example.networking.utils.Result
import com.example.zemogaapp.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(
    private val useCase: GetPostCommentsUseCase,
    private val userInfoUseCase: GetUserInfoUseCase
) : BaseViewModel() {
    private val _response = MutableLiveData<Result<List<PostCommentsResponse>>>()
    val responseUi: LiveData<Result<List<PostCommentsResponse>>> get() = _response

    fun getPostComments(id: Int) {
        viewModelScope.launch {
            useCase.getPostComments(id).collect {
                _response.postValue(it)
            }
        }
    }

    private val _userInfoResponse = MutableLiveData<Result<UserInfoResponse>>()
    val responseUserInfo: LiveData<Result<UserInfoResponse>> get() = _userInfoResponse

    fun getUserInfo(id: Int) {
        viewModelScope.launch {
            userInfoUseCase.getUserInfo(id).collect {
                _userInfoResponse.postValue(it)
            }
        }
    }
}