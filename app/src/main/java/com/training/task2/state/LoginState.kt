package com.training.task2.state

import androidx.lifecycle.MutableLiveData
import com.training.task2.model.Cartoon
import com.training.task2.model.Login.LoginData
import com.training.task2.model.Login.LoginResponse

sealed class LoginState {
    object INITIAL : LoginState()
    object LOADING : LoginState()
    object ERROR : LoginState()
    class LOGGED_IN(val loginResponse: LoginResponse) : LoginState()
    object RETRY : LoginState()
}

fun MutableLiveData<LoginState>.initialize(): MutableLiveData<LoginState> = this.apply { value = LoginState.INITIAL }
