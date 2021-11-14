package com.training.miniproject.feature.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

}