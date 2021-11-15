package com.training.miniproject.state

import com.training.miniproject.model.cartoon.Cartoon
import kotlinx.coroutines.flow.MutableStateFlow

sealed class MainState {
    object INITIAL : MainState()
    object LOADING : MainState()
    object ERROR : MainState()
    class CARTOON_RETRIEVED(val cartoon: List<Cartoon>) : MainState()
    object RETRY : MainState()
}

fun MutableStateFlow<MainState>.initialize(): MutableStateFlow<MainState> = this.apply { value = MainState.INITIAL }
