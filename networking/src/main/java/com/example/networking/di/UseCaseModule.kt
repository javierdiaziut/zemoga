package com.example.networking.di

import com.example.networking.datasource.GetPostsDataSource
import com.example.networking.usecase.GetPostInteractor
import com.example.networking.usecase.GetPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetPostsUseCase(dataSource: GetPostsDataSource): GetPostsUseCase {
        return GetPostInteractor(dataSource)
    }
}