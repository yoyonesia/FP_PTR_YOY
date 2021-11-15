package com.training.miniproject.feature.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.training.miniproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity(), UserInteractionListener {
    private var userInteractionListener: UserInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        if (userInteractionListener != null)
            userInteractionListener!!.onUserInteraction()
    }

    fun setUserInteractionListener(userInteractionListener: UserInteractionListener?) {
        this.userInteractionListener = userInteractionListener
    }
}

interface UserInteractionListener {
    fun onUserInteraction()
}