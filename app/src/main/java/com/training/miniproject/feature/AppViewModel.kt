package com.training.miniproject.feature

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.training.miniproject.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*

private val TAG = "AppViewModel"

open class AppViewModel: ViewModel() {
    private val appStateMutable = MutableLiveData<AppState>().apply { value = AppState.NORMAL }
    val appState: LiveData<AppState> = appStateMutable

    private var timer:Timer? = null

    fun postTimeoutState() = appStateMutable.postValue(AppState.TIMEOUT)

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

fun LiveData<AppState>.onSessionLogout(lifecycleOwner: LifecycleOwner, block: () -> Unit) =
    observe(lifecycleOwner) {
        if (it == AppState.TIMEOUT) block.invoke()
    }

enum class AppState {
    NORMAL,
    TIMEOUT;
}