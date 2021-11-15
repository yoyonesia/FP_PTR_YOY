package com.training.miniproject.repository.api

import android.util.Log
import com.training.miniproject.feature.db.UserDao
import com.training.miniproject.model.login.User
import com.training.miniproject.repository.mapper.toUser
import com.training.miniproject.repository.mapper.toUserEntity
import javax.inject.Inject

interface UserRepository{
    suspend fun getSavedUser(): User?
    suspend fun replaceUser(user: User)
    suspend fun deleteUser()
}

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): UserRepository {
    private val TAG = "User"
    override suspend fun getSavedUser(): User? =
        userDao.getUser()?.toUser()

    override suspend fun replaceUser(user: User) {
        Log.d(TAG, "replace user : $user")
        val userEntity = user.toUserEntity()
        userDao.replaceAllUser(userEntity)
        Log.d(TAG, "get current user : ${getSavedUser()}")
    }

    override suspend fun deleteUser() {
        userDao.deleteAllUser()
    }

}