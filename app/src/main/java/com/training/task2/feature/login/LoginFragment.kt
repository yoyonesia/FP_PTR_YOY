package com.training.task2.feature.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.paging.PagingSource
import com.google.android.material.textfield.TextInputEditText
import com.training.task2.R
import com.training.task2.di.AppModule
import com.training.task2.di.LoginAPI
import com.training.task2.repository.LoginAPIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class LoginFragment(view: View) : Fragment() {
    private val TAG = "MainActivity_"
    @Inject
    @LoginAPI
    lateinit var apiService:LoginAPIService

    private val buttonView: Button = view.findViewById(R.id.loginButton)
    private val inputUsername: TextInputEditText = view.findViewById(R.id.input_username)
    private val inputPassword: TextInputEditText = view.findViewById(R.id.input_password)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    init {
        buttonView.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val response = apiService.login("454041184B0240FBA3AACD15A1F7A8BB",inputUsername.text.toString(),inputPassword.text.toString())
                withContext(Dispatchers.Main) {
                    Log.d("aa", response.body().toString())
            }
            }
        }
    }

}


