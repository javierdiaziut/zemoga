package com.example.networking.di

import com.example.domain.PostCommentsResponse
import com.example.domain.PostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMethods {

    @GET("posts")
    suspend fun getPosts(): Response<List<PostResponse>>

    @GET("comments")
    suspend fun getPostComments(@Query("postId") id: Int): Response<List<PostCommentsResponse>>
}