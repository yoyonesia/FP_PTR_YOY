package com.training.miniproject.di


import com.training.miniproject.repository.api.CartoonAPIService
import com.training.miniproject.repository.api.LoginAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @CartoonAPI
    @Provides
    @Singleton
    fun cartoonRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @CartoonAPI
    @Provides
    @Singleton
    fun cartoonApiService(@CartoonAPI retrofit: Retrofit): CartoonAPIService {
        return retrofit.create(CartoonAPIService::class.java)
    }

    @LoginAPI
    @Provides
    @Singleton
    fun loginRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://talentpool.oneindonesia.id/api/user/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @LoginAPI
    @Provides
    @Singleton
    fun loginApiService(@LoginAPI retrofit: Retrofit): LoginAPIService {
        return retrofit.create(LoginAPIService::class.java)
    }

}


