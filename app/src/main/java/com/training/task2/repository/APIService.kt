package com.training.task2.repository

import com.training.task2.model.BaseResponse
import okhttp3.ResponseBody
import retrofit2.Response

import retrofit2.http.*


interface APIService{
    @GET("character")
    suspend fun retriveCartoon(
        @Query("page") page: String
    ): BaseResponse

}