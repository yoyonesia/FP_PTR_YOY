package com.training.miniproject.repository.api

import com.training.miniproject.model.login.LoginResponse
import retrofit2.http.*

interface LoginAPIService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Header("X-API-KEY") key: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse
}