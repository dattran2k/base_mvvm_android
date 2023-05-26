package com.base.presentation.view.main.m01_home

import androidx.fragment.app.viewModels
import com.base.databinding.M01FragmentHomeBinding
import com.base.presentation.base.BaseFragment
import com.base.presentation.view.M06DemoFragment
import com.base.util.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@AndroidEntryPoint
class M01HomeFragment : BaseFragment<M01FragmentHomeBinding>(M01FragmentHomeBinding::inflate) {

    private val viewModel: M01HomeViewModel by viewModels()
    override fun initView() {
        binding.tv.setOnClickListener {
            NavigationManager.getInstance().openFragment(M06DemoFragment())
        }
        binding.swLayout.setOnRefreshListener {
            viewModel.getData()
        }
    }

    override suspend fun initObserverCreated() {
        viewModel.homeUiState.collectLatest { state ->
            Timber.e(state.toString())
            when (state) {
                is HomeUiState.Error -> {
                    binding.swLayout.isRefreshing = false
                    binding.tv.text = state.msg
                }

                is HomeUiState.Loading -> {
                    binding.tv.text = "LOADING"
                }

                is HomeUiState.Success -> {
                    binding.tv.text = state.data
                    binding.swLayout.isRefreshing = false
                }
            }
        }
    }
}