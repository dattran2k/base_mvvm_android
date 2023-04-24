package com.base.presentation.view.main.m03_discover

import androidx.fragment.app.viewModels
import com.base.base.BaseFragment
import com.base.databinding.M03FragmentDiscoverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class M03DiscoverFragment : BaseFragment<M03FragmentDiscoverBinding>(M03FragmentDiscoverBinding::inflate) {
    private val viewModel: M03DiscoverViewModel by viewModels()

    override fun initView() {

    }


    override fun getData() {

    }

}