package com.example.zemogaapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databasezemoga.entity.PostEntity
import com.example.domain.PostItem
import com.example.networking.repository.local.LocalPostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LocalStorageViewModel @Inject constructor(private val dbRepository: LocalPostsRepository) :
    ViewModel() {

    val localItems = MutableLiveData<List<PostEntity>>()

    fun insertAllPosts(posts: List<PostItem>) {
        viewModelScope.launch {
            dbRepository.insertAllPosts(posts)
        }
    }

    fun getLocalPosts() {
        viewModelScope.launch {
            dbRepository.getAllPosts().collect {
                localItems.postValue(it)
            }
        }
    }

    fun getFavoritesPosts() {
        viewModelScope.launch {
            dbRepository.getAllFavoritesPosts().collect {
                localItems.postValue(it)
            }
        }
    }

}