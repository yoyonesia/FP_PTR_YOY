package com.training.miniproject.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("data")
    val loginData: LoginData,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("status")
    val status: Boolean,
    @field:SerializedName("token")
    val token: String
)