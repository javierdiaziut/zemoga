package com.example.networking.utils

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Loading<T>(val data: T?) : Result<T>()
    data class Failure<T>(val message: String) : Result<T>()
}