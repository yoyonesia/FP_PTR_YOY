package com.training.miniproject.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.miniproject.repository.api.UserRepository
import com.training.miniproject.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val loginStateMutable = MutableStateFlow<LoginState>(LoginState.INITIAL)
    val loginState = loginStateMutable.asStateFlow()

    fun checkLoginState() = viewModelScope.launch{
        loginStateMutable.value = LoginState.LOADING
        userRepository.getSavedUser().let {
            when(it){
                null -> loginStateMutable.value = LoginState.NOT_LOGGED_IN
                else -> loginStateMutable.value = LoginState.LOGGED_IN(it)
            }
        }
    }

}