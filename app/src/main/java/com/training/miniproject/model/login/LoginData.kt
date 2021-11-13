package com.training.miniproject.model.login

import com.google.gson.annotations.SerializedName

data class LoginData(
    val avatar: String,
    val banned: String,
    @field:SerializedName("company_id")
    val companyId: String,
    @field:SerializedName("date_created")
    val dateCreated: String,
    val email: String,
    @field:SerializedName("forgot_exp")
    val forgotExp: Any,
    @field:SerializedName("full_name")
    val fullName: String,
    val id: String,
    @field:SerializedName("ip_address")
    val ipAddress: String,
    @field:SerializedName("last_activity")
    val lastActivity: String,
    @field:SerializedName("last_login")
    val lastLogin: String,
    @field:SerializedName("oauth_provider")
    val oauthProvider: Any,
    @field:SerializedName("oauth_uid")
    val oauthUid: Any,
    @field:SerializedName("remember_exp")
    val rememberExp: Any,
    @field:SerializedName("remember_time")
    val rememberTime: Any,
    @field:SerializedName("top_secret")
    val topSecret: Any,
    val username: String,
    @field:SerializedName("verification_code")
    val verificationCode: Any
)