package com.training.task2.di

import com.training.task2.model.Cartoon
import com.training.task2.repository.APIService
import com.training.task2.repository.LoginAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
annotation class LoginAPI
@Qualifier
annotation class CartoonAPI
@Module

@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    @CartoonAPI
    fun cartoonRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @CartoonAPI
    fun cartoonApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    @Singleton
    @LoginAPI
    fun loginRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://talentpool.oneindonesia.id/api/user/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @LoginAPI
    fun loginApiService(retrofit: Retrofit): LoginAPIService {
        return retrofit.create(LoginAPIService::class.java)
    }
}

