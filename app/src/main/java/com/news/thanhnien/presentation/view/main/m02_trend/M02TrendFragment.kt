package com.news.thanhnien.presentation.view.main.m02_trend

import androidx.fragment.app.viewModels
import com.news.thanhnien.base.BaseFragment
import com.news.thanhnien.databinding.M02FragmentTrendBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class M02TrendFragment : BaseFragment<M02FragmentTrendBinding>(M02FragmentTrendBinding::inflate){
    private val viewModel: M02TrendViewModel by viewModels()
    override fun initView() {

    }

    override fun initObserver() {

    }

    override fun getData() {

    }
}