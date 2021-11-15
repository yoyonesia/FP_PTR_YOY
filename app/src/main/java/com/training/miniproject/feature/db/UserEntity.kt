package com.training.miniproject.feature.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserEntity")
data class UserEntity(
    @PrimaryKey val id: String,
    val avatar: String?,
    val banned: String?,
    val companyId: String?,
    val dateCreated: String?,
    val email: String?,
    val forgotExp: String?,
    val fullName: String?,
    val ipAddress: String?,
    val lastActivity: String?,
    val lastLogin: String?,
    val oauthProvider: String?,
    val oauthUid: String?,
    val rememberExp: String?,
    val rememberTime: String?,
    val topSecret: String?,
    val username: String?,
    val verificationCode: String?
)
