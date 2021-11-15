package com.training.miniproject.feature.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.training.miniproject.databinding.FragmentSplashBinding
import com.training.miniproject.model.login.User
import com.training.miniproject.state.LoginState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.loginState.collectLatest{
                when(it){
                    is LoginState.LOGGED_IN -> navigateToMainPage(it.user)
                    is LoginState.NOT_LOGGED_IN -> navigateToLoginPage()
                }
            }
        }

        val ivLogo = binding.ivLogo
        ivLogo.alpha = 0f
        ivLogo.animate().setDuration(2000).alpha(1f).withEndAction{
            viewModel.checkLoginState()
        }

    }

    private fun navigateToLoginPage(){
        val navDirection = SplashFragmentDirections.actionLogin()
        findNavController().navigate(navDirection)
    }

    private fun navigateToMainPage(user: User){
        val navDirection = SplashFragmentDirections.actionMain(user)
        findNavController().navigate(navDirection)
    }

}