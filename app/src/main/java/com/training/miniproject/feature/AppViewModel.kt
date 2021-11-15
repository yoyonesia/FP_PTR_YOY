package com.training.miniproject.feature

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.training.miniproject.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import java.util.*

private val TAG = "AppViewModel"

open class AppViewModel: ViewModel() {
    private val appStateMutable = MutableStateFlow<AppState>(AppState.NORMAL)
    val appState = appStateMutable.asStateFlow()

    private var timer:Timer? = null

    fun postTimeoutState() {
        appStateMutable.value = AppState.TIMEOUT
    }

    fun startUserSession() {
        cancelTimer()
        Log.d(TAG, "startUsesSession")
        timer = Timer()
        timer?.schedule(object: TimerTask(){
            override fun run() {
                Log.d(TAG, "onSessionLogout")
                postTimeoutState()
            }

        },BuildConfig.SESSION_TIMEOUT.toLong())
    }

    fun cancelTimer(){
        if (timer != null) timer?.cancel()
    }

}

suspend fun StateFlow<AppState>.onSessionLogout( block: () -> Unit) =
    collectLatest {
        if (it == AppState.TIMEOUT) block.invoke()
    }

enum class AppState {
    NORMAL,
    TIMEOUT;
}