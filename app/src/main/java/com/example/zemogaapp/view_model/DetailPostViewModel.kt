package com.example.zemogaapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.PostCommentsResponse
import com.example.domain.PostResponse
import com.example.networking.usecase.GetPostCommentsUseCase
import com.example.networking.usecase.GetPostsUseCase
import com.example.networking.utils.Result
import com.example.zemogaapp.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(private val useCase: GetPostCommentsUseCase) : BaseViewModel() {
    private val _response = MutableLiveData<Result<List<PostCommentsResponse>>>()
    val responseUi: LiveData<Result<List<PostCommentsResponse>>> get() = _response

    fun getPostComments(id : Int) {
        viewModelScope.launch {
            useCase.getPostComments(id).collect {
                _response.postValue(it)
            }
        }
    }
}