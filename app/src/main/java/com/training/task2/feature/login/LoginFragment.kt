package com.training.task2.feature.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.training.task2.databinding.FragmentLoginBinding

import com.training.task2.di.LoginAPI
import com.training.task2.model.Login.LoginData
import com.training.task2.model.Login.LoginResponse
import com.training.task2.repository.LoginAPIService
import com.training.task2.state.LoginState
import com.training.task2.ui.dismissAllDialogs
import com.training.task2.ui.showFailLoadDataDialog
import com.training.task2.ui.showLoadingDialog
import com.training.task2.util.SessionManagerUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment: Fragment() {
    var token =""
    private val TAG = "MainActivity_"
    private lateinit var binding:FragmentLoginBinding
    private lateinit var loginAdapter: LoginAdapter
    @LoginAPI @Inject lateinit var apiService: LoginAPIService
    private val viewModel:LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loginState.observe(viewLifecycleOwner){
            when(it){
                is LoginState.LOADING -> showLoadingDialog()
                is LoginState.LOGGED_IN -> handleLoggedInState(it.loginResponse)
                is LoginState.ERROR -> showFailLoadDataDialog {  }
            }
        }
        binding.loginButton.setOnClickListener {
            viewModel.login(binding.inputUsername.text.toString(),binding.inputPassword.text.toString())

        }
    }

    private fun handleLoggedInState(loginResponse: LoginResponse) {
        val loginData = loginResponse.loginData
        context?.let { SessionManagerUtil().getInstance()?.setUser(it,loginData.username) }
        token = loginResponse.token
        startAndStoreSession()
        dismissAllDialogs()
        navigateToHome()
    }


    private fun navigateToHome(){
        val navDirection = LoginFragmentDirections.actionMain()

        findNavController().navigate(navDirection)
    }

    fun startAndStoreSession() {
        context?.let {
            SessionManagerUtil().getInstance()
                ?.storeUserToken(it, token)
        }
        context?.let { SessionManagerUtil().getInstance()?.startUserSession(it, 300) }
    }
}


