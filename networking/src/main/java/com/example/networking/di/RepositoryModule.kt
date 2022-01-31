package com.example.networking.di

import com.example.databasezemoga.dao.PostDao
import com.example.databasezemoga.db.AppDatabase
import com.example.networking.datasource.GetPostsDataSource
import com.example.networking.repository.GetPostRepository
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
    fun provideChannelDao(appDatabase: AppDatabase): PostDao {
        return appDatabase.postDao()
    }
}