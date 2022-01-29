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
annotation class ApiMethodsShareKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpByFingerprint

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpByShareKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockInterceptorName

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KeyGeneratorArr
