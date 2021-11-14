package com.training.miniproject.di

import android.content.Context
import android.content.SharedPreferences
import com.training.miniproject.BuildConfig
import com.training.miniproject.feature.db.UserDao
import com.training.miniproject.repository.LoginRepository
import com.training.miniproject.repository.LoginRepositoryImpl
import com.training.miniproject.repository.api.LoginAPIService
import com.training.miniproject.repository.api.UserRepository
import com.training.miniproject.repository.api.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSharedPreference(
        @ApplicationContext context: Context
    ): SharedPreferences = context.getSharedPreferences(
        BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)


    @Singleton
    @Provides
    fun provideLoginRepository(
        @LoginAPI loginAPIService: LoginAPIService,
        sharedPreferences: SharedPreferences,
        userRepository: UserRepository
    ): LoginRepository =
        LoginRepositoryImpl(
            loginAPIService,
            sharedPreferences,
            userRepository
        )


    @Singleton
    @Provides
    fun provideUserRepository(
        userDao: UserDao
    ): UserRepository =
        UserRepositoryImpl(
            userDao
        )
}