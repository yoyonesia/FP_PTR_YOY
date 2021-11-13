package com.training.miniproject.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("data")
    val loginData: LoginData,
    val message: String,
    val status: Boolean,
    val token: String
)