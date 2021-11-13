package com.training.miniproject.feature.login



import android.util.Log
import com.training.miniproject.di.LoginAPI
import com.training.miniproject.repository.api.LoginAPIService

import javax.inject.Inject

import javax.inject.Singleton

@Singleton
class LoginAdapter @Inject constructor(@LoginAPI private val apiService: LoginAPIService) {

    suspend fun hitAPILogin(username:String,password:String){
        val response = apiService.login("454041184B0240FBA3AACD15A1F7A8BB",username,password);
        Log.d("response",response.toString())
    }
}