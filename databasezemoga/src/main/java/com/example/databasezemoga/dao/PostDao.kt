package com.example.databasezemoga.dao

import androidx.room.*
import com.example.databasezemoga.entity.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM post_table")
    fun getAllPost(): Flow<List<PostEntity>>

    @Query("SELECT * FROM post_table WHERE isFavorite = :isFavorite")
    fun getAllFavoritePost(isFavorite : Boolean): Flow<List<PostEntity>>

    @Update
    suspend fun updatePost(item: PostEntity)

    @Query("DELETE FROM post_table")
    fun deleteAllPosts()

    @Delete
    suspend fun deletePost(item: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(allPosts: List<PostEntity>)
}