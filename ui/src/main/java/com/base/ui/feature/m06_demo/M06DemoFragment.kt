package com.base.ui.feature.m06_demo

import com.bumptech.glide.Glide
import com.base.ui.databinding.M06FragmentDemoBinding
import com.base.ui.feature.BaseFragment
import com.base.ui.feature.popBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class M06DemoFragment : BaseFragment<M06FragmentDemoBinding>(M06FragmentDemoBinding::inflate) {

    override fun initView() {
        Glide.with(requireContext())
            .load("https://d5nunyagcicgy.cloudfront.net/external_assets/hero_examples/hair_beach_v391182663/original.jpeg")
            .into(binding.image)
        binding.image.setOnClickListener {
            popBackStack()
        }
    }

}