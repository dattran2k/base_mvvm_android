package com.base.presentation.view

import com.base.presentation.base.BaseFragment
import com.base.databinding.M06FragmentDemoBinding
import com.base.presentation.base.popBackStack
import com.bumptech.glide.Glide
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