package com.base.ui.feature.m03_discover

import androidx.fragment.app.viewModels
import com.base.ui.databinding.M03FragmentDiscoverBinding
import com.base.ui.feature.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class M03DiscoverFragment : BaseFragment<M03FragmentDiscoverBinding>(M03FragmentDiscoverBinding::inflate) {
    private val viewModel: M03DiscoverViewModel by viewModels()

    override fun initView() {

    }


   

}