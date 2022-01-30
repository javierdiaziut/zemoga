package com.example.networking.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiMethodsFingerprint


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpByFingerprint


