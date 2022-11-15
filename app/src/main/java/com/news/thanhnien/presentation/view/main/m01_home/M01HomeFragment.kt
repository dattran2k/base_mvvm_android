package com.news.thanhnien.presentation.view.main.m01_home

import androidx.fragment.app.viewModels
import com.news.thanhnien.base.BaseFragment
import com.news.thanhnien.databinding.M01FragmentHomeBinding
import com.news.thanhnien.helper.NavigationManager
import com.news.thanhnien.presentation.view.M06DemoFragment
import dagger.hilt.android.AndroidEntryPoint

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

    override fun initObserver() {

    }

    override fun getData() {
        viewModel.getData()
    }

}