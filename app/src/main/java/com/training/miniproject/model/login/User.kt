package com.training.miniproject.model.login

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @field:SerializedName("avatar")
    val avatar: String?,
    @field:SerializedName("banned")
    val banned: String?,
    @field:SerializedName("company_id")
    val companyId: String?,
    @field:SerializedName("date_created")
    val dateCreated: String?,
    @field:SerializedName("email")
    val email: String?,
    @field:SerializedName("forgot_exp")
    val forgotExp: String?,
    @field:SerializedName("full_name")
    val fullName: String?,
    @field:SerializedName("id")
    val id: String?,
    @field:SerializedName("ip_address")
    val ipAddress: String?,
    @field:SerializedName("last_activity")
    val lastActivity: String?,
    @field:SerializedName("last_login")
    val lastLogin: String?,
    @field:SerializedName("oauth_provider")
    val oauthProvider: String?,
    @field:SerializedName("oauth_uid")
    val oauthUid: String?,
    @field:SerializedName("remember_exp")
    val rememberExp: String?,
    @field:SerializedName("remember_time")
    val rememberTime: String?,
    @field:SerializedName("top_secret")
    val topSecret: String?,
    @field:SerializedName("username")
    val username: String?,
    @field:SerializedName("verification_code")
    val verificationCode: String?
): Parcelable