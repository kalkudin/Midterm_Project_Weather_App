package com.example.midtermproject.auth_feature.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.midtermproject.R
import com.example.midtermproject.databinding.FragmentHomeLayoutBinding
import com.example.midtermproject.auth_feature.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeLayoutBinding>(FragmentHomeLayoutBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun bindViewActionListeners() {
        bindLoginBtn()
        bindRegisterBtn()
    }

    override fun bindObservers() {
        bindNavigationEvents()
    }

    private fun bindLoginBtn() {
        binding.btnLogin.setOnClickListener {
            homeViewModel.onEvent(HomeEvent.NavigateToLogin)
        }
    }

    private fun bindRegisterBtn() {
        binding.btnRegister.setOnClickListener {
            homeViewModel.onEvent(HomeEvent.NavigateToRegister)
        }
    }

    private fun bindNavigationEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.navigationFlow.collect { event ->
                    when (event) {
                        is NavigationEvent.NavigateToLogin -> navigateToLogin()
                        is NavigationEvent.NavigateToRegister -> navigateToRegister()
                        is NavigationEvent.NavigateToMap -> navigateToMap()
                        is NavigationEvent.RemainOnCurrentPage -> remainOnCurrentPage()
                    }
                }
            }
        }
    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
    }

    private fun navigateToRegister() {
        findNavController().navigate(R.id.action_homeFragment_to_registerFragment)
    }

    private fun navigateToMap(){
        findNavController().navigate(R.id.action_loginFragment_to_mapFragment)
    }

    private fun remainOnCurrentPage(){

    }
}