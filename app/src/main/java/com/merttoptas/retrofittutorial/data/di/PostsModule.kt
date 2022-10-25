package com.merttoptas.retrofittutorial.data.di

import com.merttoptas.retrofittutorial.data.local.database.PostsDatabase
import com.merttoptas.retrofittutorial.data.remote.api.PostApiService
import com.merttoptas.retrofittutorial.data.repository.PostRepository
import com.merttoptas.retrofittutorial.data.repository.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

/**
 * Created by merttoptas on 17.10.2022.
 */

@Module
@InstallIn(ViewModelComponent::class)
class PostsModule {
    // Api Service Class
    // Repository and DataSource Impl
    // Database

    @Provides
    fun provideApiService(retrofit: Retrofit) : PostApiService {
        return retrofit.create(PostApiService::class.java)
    }

    @Provides
    fun providePostRepository(postApiService: PostApiService, postsDatabase: PostsDatabase) : PostRepository {
        return PostRepositoryImpl(postApiService, postsDatabase)
    }
}