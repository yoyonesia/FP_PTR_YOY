package com.training.miniproject.state

import com.training.miniproject.model.login.LoginResponse
import com.training.miniproject.model.login.User
import kotlinx.coroutines.flow.MutableStateFlow

sealed class LoginState {
    object INITIAL : LoginState()
    object LOADING : LoginState()
    object ERROR : LoginState()
    class LOGGED_IN(val user: User) : LoginState()
    object NOT_LOGGED_IN : LoginState()
    class LOGIN_SUCCESS(val loginResponse: LoginResponse) : LoginState()
    object RETRY : LoginState()
}

fun MutableStateFlow<LoginState>.initialize(): MutableStateFlow<LoginState> = this.apply { value = LoginState.INITIAL }
