package com.example.networking.repository.local

import com.example.databasezemoga.db.AppDatabase
import com.example.databasezemoga.entity.PostEntity
import com.example.domain.PostItem
import com.example.domain.toEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalPostsRepository @Inject constructor(private val appDatabase: AppDatabase) {

    suspend fun insertAllPosts(post: List<PostItem>) {

        val list = post.map { it.toEntity() }
        return appDatabase.postDao()
            .insertAllPosts(list)
    }

    suspend fun deletePost(item: PostEntity) {
        appDatabase.postDao().deletePost(item)
    }

    suspend fun deleteAllPost() {
        appDatabase.postDao().deleteAllPosts()
    }

    suspend fun updatePost(item: PostEntity) {
        appDatabase.postDao().updatePost(item)
    }

    fun getAllPosts(): Flow<List<PostEntity>> {
        return appDatabase.postDao().getAllPost()
    }

    fun getAllFavoritesPosts(): Flow<List<PostEntity>> {
        return appDatabase.postDao().getAllFavoritePost(isFavorite = true)
    }

}