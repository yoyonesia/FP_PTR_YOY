package com.training.miniproject.repository.mapper

import com.training.miniproject.feature.db.UserEntity
import com.training.miniproject.model.login.User

    fun User.toUserEntity(): UserEntity{
        val id = this.id ?: throw NoSuchElementException("id")
        val avatar = this.avatar
        val banned = this.banned
        val companyId = this.companyId
        val dateCreated = this.dateCreated
        val email = this.email
        val forgotExp = this.forgotExp
        val fullName = this.fullName
        val ipAddress = this.ipAddress
        val lastActivity = this.lastActivity
        val lastLogin = this.lastLogin
        val oauthProvider = this.oauthProvider
        val oauthUid = this.oauthUid
        val rememberExp = this.rememberExp
        val rememberTime = this.rememberTime
        val topSecret = this.topSecret
        val username = this.username
        val verificationCode = this.verificationCode
        return UserEntity(id, avatar, banned, companyId, dateCreated, email, forgotExp, fullName, ipAddress, lastActivity, lastLogin, oauthProvider, oauthUid, rememberExp, rememberTime, topSecret, username, verificationCode)
    }

    fun UserEntity.toUser(): User{
        val id = this.id
        val avatar = this.avatar
        val banned = this.banned
        val companyId = this.companyId
        val dateCreated = this.dateCreated
        val email = this.email
        val forgotExp = this.forgotExp
        val fullName = this.fullName
        val ipAddress = this.ipAddress
        val lastActivity = this.lastActivity
        val lastLogin = this.lastLogin
        val oauthProvider = this.oauthProvider
        val oauthUid = this.oauthUid
        val rememberExp = this.rememberExp
        val rememberTime = this.rememberTime
        val topSecret = this.topSecret
        val username = this.username
        val verificationCode = this.verificationCode
        return User(avatar, banned, companyId, dateCreated, email, forgotExp, fullName, id, ipAddress, lastActivity, lastLogin, oauthProvider, oauthUid, rememberExp, rememberTime, topSecret, username, verificationCode)
    }