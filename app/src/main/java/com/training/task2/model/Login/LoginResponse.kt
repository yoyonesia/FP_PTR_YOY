package com.training.task2.model.Login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("data")
    val loginData: LoginData,
    val message: String,
    val status: Boolean,
    val token: String
)