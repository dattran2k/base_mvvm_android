package com.base.presentation.view.m00_main.m01_home

import androidx.fragment.app.viewModels
import com.base.databinding.M01FragmentHomeBinding
import com.base.presentation.BaseFragment
import com.base.presentation.navigate
import com.base.presentation.view.m00_main.M00MainFragmentDirections
import com.base.presentation.view.m00_main.m05_user.M05UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class M01HomeFragment : BaseFragment<M01FragmentHomeBinding>(M01FragmentHomeBinding::inflate) {

    private val viewModel: M01HomeViewModel by viewModels()
    private val viewModel2: M05UserViewModel by viewModels()
    override fun initView() {
        binding.tv.setOnClickListener {
            navigate(M00MainFragmentDirections.actionM00MainFragmentToM06DemoFragment())
        }
        binding.swLayout.setOnRefreshListener {
            viewModel.updateData()
        }

    }

    override fun CoroutineScope.initObserverCreated() {
        launch {
            viewModel.homeUIState.collectLatest { state ->
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
        // this for test 2 state same time, removeAble
        launch {
            viewModel2.darkModeState.collect {
                Timber.e(it.toString())
            }
        }
    }
}