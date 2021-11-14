package com.training.miniproject.feature.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

import com.training.miniproject.databinding.FragmentLoginBinding
import com.training.miniproject.model.login.User

import com.training.miniproject.model.login.LoginResponse
import com.training.miniproject.state.LoginState
import com.training.miniproject.ui.dismissAllDialogs
import com.training.miniproject.ui.showFailLoadDataDialog
import com.training.miniproject.ui.showLoadingDialog
import com.training.miniproject.ui.showLoginFailedDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LoginFragment: Fragment() {

    private val TAG = "Login"
    private lateinit var binding:FragmentLoginBinding
    private val viewModel:LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity!!.finish()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.loginState.collectLatest{
                when(it){
                    is LoginState.LOADING -> showLoadingDialog()
                    is LoginState.LOGIN_SUCCESS -> handleLoggedInState(it.loginResponse)
                    is LoginState.ERROR -> showLoginFailedDialog {  }
                }
            }
        }
        binding.loginButton.setOnClickListener {
            viewModel.login(binding.inputUsername.text.toString(),binding.inputPassword.text.toString())

        }
    }

    private fun handleLoggedInState(loginResponse: LoginResponse) {
        dismissAllDialogs()
        navigateToHome(loginResponse.user)
    }


    private fun navigateToHome(user: User){
        val navDirection = LoginFragmentDirections.actionMain(user)
        findNavController().navigate(navDirection)
    }

}


