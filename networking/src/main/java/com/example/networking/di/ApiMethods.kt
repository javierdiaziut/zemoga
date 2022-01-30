package com.example.networking.di

import com.example.domain.PostResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiMethods {

    @GET("posts")
    suspend fun getPosts(): Response<List<PostResponse>>
}