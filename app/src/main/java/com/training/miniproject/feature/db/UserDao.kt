package com.training.miniproject.feature.db

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Update
    suspend fun editUser(userEntity: UserEntity)

    @Delete
    suspend fun deleteUser(userEntity: UserEntity)

    @Query("DELETE FROM UserEntity")
    suspend fun deleteAllUser()

    @Transaction
    suspend fun replaceAllUser(userEntity: UserEntity) {
        deleteAllUser()
        insertUser(userEntity)
    }

}