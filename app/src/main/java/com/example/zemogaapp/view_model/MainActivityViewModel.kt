package com.example.zemogaapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.PostResponse
import com.example.networking.usecase.GetPostsUseCase
import com.example.networking.utils.Result
import com.example.zemogaapp.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val useCase: GetPostsUseCase) : BaseViewModel() {
    private val _response = MutableLiveData<Result<List<PostResponse>>>()
    val responseUi: LiveData<Result<List<PostResponse>>> get() = _response

    fun getPosts() {
        viewModelScope.launch {
            useCase.getPosts().collect {
                _response.postValue(it)
            }
        }
    }
}