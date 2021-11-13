package com.training.miniproject.feature.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.training.miniproject.databinding.FragmentLoginBinding
import com.training.miniproject.model.login.LoginData

import com.training.miniproject.model.login.LoginResponse
import com.training.miniproject.state.LoginState
import com.training.miniproject.ui.dismissAllDialogs
import com.training.miniproject.ui.showFailLoadDataDialog
import com.training.miniproject.ui.showLoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment() {

    private val TAG = "Login"
    private lateinit var binding:FragmentLoginBinding
    private val viewModel:LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        dismissAllDialogs()
        navigateToHome(loginResponse.loginData)
    }


    private fun navigateToHome(loginData: LoginData){
        val navDirection = LoginFragmentDirections.actionMain(loginData)
        findNavController().navigate(navDirection)
    }

}


