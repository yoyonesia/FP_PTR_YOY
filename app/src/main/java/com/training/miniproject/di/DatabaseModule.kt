package com.training.miniproject.di

import android.content.Context
import androidx.room.Room
import com.training.miniproject.feature.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "MiniProjectDatabase"
    ).build()

    @Singleton
    @Provides
    fun provideUserDao(
        database: AppDatabase
    ) = database.userDao()
}