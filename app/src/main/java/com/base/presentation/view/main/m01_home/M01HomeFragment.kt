package com.base.presentation.view.main.m01_home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.base.base.BaseFragment
import com.base.base.CommonState
import com.base.databinding.M01FragmentHomeBinding
import com.base.helper.NavigationManager
import com.base.presentation.view.M06DemoFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class M01HomeFragment : BaseFragment<M01FragmentHomeBinding>(M01FragmentHomeBinding::inflate) {

    private val viewModel: M01HomeViewModel by viewModels()
    override fun initView() {
        binding.tv.setOnClickListener {
            NavigationManager.getInstance().openFragment(
                M06DemoFragment(),
                false
            )
        }
    }

    override suspend fun initObserverCreated() {
        viewModel.homeState.collect { state ->
            Timber.d(state.toString())
            when (state) {
                is CommonState.Init -> {
                }

                is CommonState.Error -> {
                }

                is CommonState.Loading -> {
                }

                is CommonState.Success -> {
                }
            }
        }
    }

    override fun getData() {
        viewModel.getData()
    }

}