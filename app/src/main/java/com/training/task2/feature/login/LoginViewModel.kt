package com.training.task2.feature.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.task2.di.LoginAPI
import com.training.task2.repository.LoginAPIService
import com.training.task2.state.LoginState
import com.training.task2.util.SessionManagerUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(@LoginAPI private val apiService:LoginAPIService):ViewModel() {

    private val loginStateMutable= MutableLiveData<LoginState>().apply { LoginState.INITIAL }
    val loginState:LiveData<LoginState> = loginStateMutable

    fun login(username:String,password:String)=viewModelScope.launch{
        loginStateMutable.value = LoginState.LOADING
        apiService.login("454041184B0240FBA3AACD15A1F7A8BB",username,password).let {
             if(it !=null && it.status){
                 loginStateMutable.value = LoginState.LOGGED_IN(it)

             }else{
                 loginStateMutable.value = LoginState.ERROR
             }
         }

    }
}