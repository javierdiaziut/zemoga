package com.example.networking.di

import com.example.databasezemoga.dao.PostDao
import com.example.databasezemoga.db.AppDatabase
import com.example.networking.datasource.GetPostCommentsDataSource
import com.example.networking.datasource.GetPostsDataSource
import com.example.networking.datasource.GetUserInfoDataSource
import com.example.networking.repository.GetPostCommentsRepository
import com.example.networking.repository.GetPostRepository
import com.example.networking.repository.GetUserInfoRepository
import com.example.networking.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    fun provideGetPostRepository(
        @ApiMethodsFingerprint
        apiMethods: ApiMethods,
        dispatcherProvider: DispatcherProvider
    ): GetPostsDataSource {
        return GetPostRepository(
            apiMethods,
            dispatcherProvider
        )
    }

    @Provides
    fun provideGetPosCommentsRepository(
        @ApiMethodsFingerprint
        apiMethods: ApiMethods,
        dispatcherProvider: DispatcherProvider
    ): GetPostCommentsDataSource {
        return GetPostCommentsRepository(
            apiMethods,
            dispatcherProvider
        )
    }

    @Provides
    fun provideGetUserInfoRepository(
        @ApiMethodsFingerprint
        apiMethods: ApiMethods,
        dispatcherProvider: DispatcherProvider
    ): GetUserInfoDataSource {
        return GetUserInfoRepository(
            apiMethods,
            dispatcherProvider
        )
    }

    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): PostDao {
        return appDatabase.postDao()
    }
}