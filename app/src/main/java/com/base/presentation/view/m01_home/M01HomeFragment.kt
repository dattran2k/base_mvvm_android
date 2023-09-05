package com.base.presentation.view.m01_home

import androidx.fragment.app.viewModels
import com.base.databinding.M01FragmentHomeBinding
import com.base.presentation.BaseFragment
import com.base.presentation.view.m06_demo.M06DemoFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class M01HomeFragment : BaseFragment<M01FragmentHomeBinding>(M01FragmentHomeBinding::inflate) {

    private val viewModel: M01HomeViewModel by viewModels()
    override fun initView() {
        binding.tv.setOnClickListener {
            navigationManager.openFragment(M06DemoFragment())
        }
        binding.swLayout.setOnRefreshListener {
            viewModel.refreshData()
        }
    }

    override fun initObserver() {
        viewModel.homeUIState.observe(viewLifecycleOwner) { state ->
            Timber.e(state.toString())
            when (state) {
                is HomeUIState.Error -> {
                    binding.swLayout.isRefreshing = false
                    binding.tv.text = state.msg
                }

                is HomeUIState.Loading -> {
                    binding.tv.text = "LOADING"
                }

                is HomeUIState.Success -> {
                    binding.tv.text = state.data.take(5).joinToString(separator = "\n")
                    binding.swLayout.isRefreshing = false
                }
            }
        }
    }

}