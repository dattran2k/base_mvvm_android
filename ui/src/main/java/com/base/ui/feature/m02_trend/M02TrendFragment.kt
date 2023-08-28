package com.base.ui.feature.m02_trend

import androidx.fragment.app.viewModels
import com.base.ui.databinding.M02FragmentTrendBinding
import com.base.ui.feature.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class M02TrendFragment : BaseFragment<M02FragmentTrendBinding>(M02FragmentTrendBinding::inflate){
    private val viewModel: M02TrendViewModel by viewModels()
    override fun initView() {

    }


   
}