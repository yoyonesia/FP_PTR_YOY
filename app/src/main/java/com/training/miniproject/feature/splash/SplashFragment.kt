package com.training.miniproject.feature.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.training.miniproject.databinding.FragmentSplashBinding
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ivLogo = binding.ivLogo
        ivLogo.alpha = 0f
        ivLogo.animate().setDuration(2000).alpha(1f).withEndAction{
            navigateToHome()
        }

    }

    private fun navigateToHome(){
        val navDirection = SplashFragmentDirections.actionLogin()
        findNavController().navigate(navDirection)
    }

}