package com.training.miniproject.feature.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.miniproject.databinding.FragmentMainBinding
import com.training.miniproject.feature.onSessionLogout
import com.training.miniproject.feature.splash.SplashActivity
import com.training.miniproject.feature.splash.UserInteractionListener
import com.training.miniproject.model.cartoon.Cartoon
import com.training.miniproject.ui.showSessionTimeoutDialog
import com.training.miniproject.ui.showSucceedDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class MainFragment : Fragment(), UserInteractionListener {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val args: MainFragmentArgs by navArgs()

    lateinit var cartoonAdapter: CartoonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as SplashActivity).setUserInteractionListener(this)
        //Starts session
        viewModel.startUserSession()
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        initRecyclerView()
        initData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Observe session
        lifecycleScope.launchWhenCreated {
            viewModel.appState.onSessionLogout(){
                viewModel.logout().let {
                    showSessionTimeoutDialog()
                    navigateToLoginPage()
                }
            }
        }

        binding.tvName.text = "Welcome, ${args.user.fullName}"
    }

    override fun onUserInteraction() {
        viewModel.startUserSession()
    }

    private fun initRecyclerView(){
        binding.rvCartoon.apply {
            layoutManager = LinearLayoutManager(this@MainFragment.context)
            cartoonAdapter = CartoonAdapter()
            adapter = cartoonAdapter
        }
    }

    private fun initData(){
        lifecycleScope.launchWhenCreated {
            viewModel.getCartoons().collectLatest {
                cartoonAdapter.submitData(it)
            }
        }
    }

    private fun navigateToLoginPage(){
        val navDirection = MainFragmentDirections.actionLogin()
        findNavController().navigate(navDirection)
    }

    private fun handleUserRetrievedState(cartoon: List<Cartoon>) {
        showSucceedDialog()

    }

    private fun handleErrorState() {

    }
}