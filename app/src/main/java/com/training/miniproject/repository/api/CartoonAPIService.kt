package com.training.miniproject.repository.api

import com.training.miniproject.model.cartoon.CartoonResponse

import retrofit2.http.*


interface CartoonAPIService{
    @GET("character")
    suspend fun retriveCartoon(
        @Query("page") page: String
    ): CartoonResponse

}