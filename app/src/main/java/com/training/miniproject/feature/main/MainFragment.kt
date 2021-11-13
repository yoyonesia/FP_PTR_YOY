package com.training.miniproject.feature.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.miniproject.databinding.FragmentMainBinding
import com.training.miniproject.model.cartoon.Cartoon
import com.training.miniproject.ui.showSucceedDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    lateinit var cartoonAdapter: CartoonAdapter

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
        binding = FragmentMainBinding.inflate(inflater, container, false)

        initRecyclerView()
        initData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    private fun handleUserRetrievedState(cartoon: List<Cartoon>) {
        showSucceedDialog()

    }

    private fun handleErrorState() {

    }
}