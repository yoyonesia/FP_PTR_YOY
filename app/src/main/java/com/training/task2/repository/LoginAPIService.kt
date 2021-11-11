package com.training.task2.repository

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface LoginAPIService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Header("X-API-KEY") key:String,
                      @Field("username")username:String, @Field("password")password:String): Response<ResponseBody>
}