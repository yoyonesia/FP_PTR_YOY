package com.training.task2.feature.login



import android.util.Log
import com.training.task2.di.LoginAPI
import com.training.task2.repository.LoginAPIService

import javax.inject.Inject

import javax.inject.Singleton

@Singleton
class LoginAdapter @Inject constructor(@LoginAPI private val apiService: LoginAPIService) {

    suspend fun hitAPILogin(username:String,password:String){
        val response = apiService.login("454041184B0240FBA3AACD15A1F7A8BB",username,password);
        Log.d("response",response.toString())
    }
}