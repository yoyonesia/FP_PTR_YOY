package com.training.miniproject.state

import androidx.lifecycle.MutableLiveData
import com.training.miniproject.model.cartoon.Cartoon

sealed class HomeState {
    object INITIAL : HomeState()
    object LOADING : HomeState()
    object ERROR : HomeState()
    class CARTOON_RETRIEVED(val cartoon: List<Cartoon>) : HomeState()
    object RETRY : HomeState()
}

fun MutableLiveData<HomeState>.initialize(): MutableLiveData<HomeState> = this.apply { value = HomeState.INITIAL }
