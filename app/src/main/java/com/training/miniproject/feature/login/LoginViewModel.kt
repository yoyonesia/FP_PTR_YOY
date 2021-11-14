package com.training.miniproject.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.miniproject.repository.LoginRepository
import com.training.miniproject.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
    ):ViewModel() {

    private val loginStateMutable = MutableStateFlow<LoginState>(LoginState.INITIAL)
    val loginState = loginStateMutable.asStateFlow()

    fun login(username:String,password:String)=viewModelScope.launch{
        loginStateMutable.value = LoginState.LOADING
        loginRepository.login(username,password).let {
            if(it != null){
                loginStateMutable.value = LoginState.LOGIN_SUCCESS(it)
            }else loginStateMutable.value = LoginState.ERROR
         }
    }
}