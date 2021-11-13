package com.training.miniproject.repository

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.training.miniproject.common.CommonErrorException
import com.training.miniproject.di.LoginAPI
import com.training.miniproject.model.login.LoginResponse
import com.training.miniproject.repository.api.LoginAPIService
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

private val key = "454041184B0240FBA3AACD15A1F7A8BB"

interface LoginRepository{
    suspend fun login(
        username: String,
        password: String
    ): LoginResponse?

    suspend fun getToken(): String
    suspend fun saveToken(token: String)
    suspend fun clearToken()
}


class LoginRepositoryImpl @Inject constructor(
    private val loginApiService: LoginAPIService,
    private val sharedPreferences: SharedPreferences
): LoginRepository {

    companion object {
        private const val TAG = "Login"
        private const val KEY_PREFS_LOGIN_TOKEN = "KEY_PREFS_LOGIN_TOKEN"
    }

    override suspend fun login(username: String, password: String): LoginResponse? {
        try {
            return loginApiService.login(key, username, password)
        }catch (e: Exception){
            return null
        }
    }

    override suspend fun getToken(): String {
        return sharedPreferences.getString(KEY_PREFS_LOGIN_TOKEN, "") ?: ""
    }

    override suspend fun saveToken(token: String) {
        val token = sharedPreferences.getString(KEY_PREFS_LOGIN_TOKEN, "")
        Log.d(TAG, "token before: $token")
        sharedPreferences.edit(true){
            putString(KEY_PREFS_LOGIN_TOKEN, token)
        }
        Log.d(TAG, "token after: ${sharedPreferences.getString(KEY_PREFS_LOGIN_TOKEN, "")}")
    }

    override suspend fun clearToken() {
        Log.d(TAG, "token before: ${sharedPreferences.getString(KEY_PREFS_LOGIN_TOKEN, "")}")
        sharedPreferences.edit(true){
            putString(KEY_PREFS_LOGIN_TOKEN, "")
        }
        Log.d(TAG, "token after: ${sharedPreferences.getString(KEY_PREFS_LOGIN_TOKEN, "")}")
    }
}