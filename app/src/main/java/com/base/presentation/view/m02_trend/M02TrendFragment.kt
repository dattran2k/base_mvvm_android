package com.base.presentation.view.m02_trend

import androidx.fragment.app.viewModels
import com.base.databinding.M02FragmentTrendBinding
import com.base.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class M02TrendFragment : BaseFragment<M02FragmentTrendBinding>(M02FragmentTrendBinding::inflate) {
    private val viewModel: M02TrendViewModel by viewModels()
    override fun initView() {

    }

    override fun initObserver() {

    }


}