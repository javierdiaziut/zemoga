package com.example.networking.di

import android.content.Context
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl() : String =  ""

    @ApiMethodsFingerprint
    @Provides
    fun provideApiMethodsByFingerprint(@BaseUrl baseUrl : String, @OkhttpByFingerprint okHttpClient: OkHttpClient) : ApiMethods {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiMethods::class.java)
    }

    @OkhttpByFingerprint
    @Provides
    fun providesOkhttpClient(
        networkConnectivityInterceptor: NetworkConnectivityInterceptor,

    ) : OkHttpClient{

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient().newBuilder()
        httpClient.readTimeout(1000, TimeUnit.SECONDS)
        httpClient.connectTimeout(1000, TimeUnit.SECONDS)
        httpClient.writeTimeout(1000, TimeUnit.SECONDS)
        httpClient.retryOnConnectionFailure(false)


        val interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Connection","close")
                .method(chain.request().method, chain.request().body)
                .build()
            chain.proceed(request)
        }

        httpClient.addInterceptor(logInterceptor)
        httpClient.networkInterceptors().add(interceptor)
        httpClient.addInterceptor(networkConnectivityInterceptor)

        return httpClient.build()
    }

    @Provides
    fun providesNetworkInterceptor(@ApplicationContext context: Context) : NetworkConnectivityInterceptor {
        return NetworkConnectivityInterceptor(
            context
        )
    }
}