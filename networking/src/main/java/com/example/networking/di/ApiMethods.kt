package com.example.networking.di

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiMethods {

    @GET("")
    suspend fun checkCommunication(@Body request: Any): Response<Any>
}