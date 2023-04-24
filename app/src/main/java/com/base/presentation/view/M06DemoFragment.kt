package com.base.presentation.view

import com.base.base.BaseFragment
import com.base.databinding.M06FragmentDemoBinding
import com.base.helper.NavigationManager
import com.base.helper.Utility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class M06DemoFragment : BaseFragment<M06FragmentDemoBinding>(M06FragmentDemoBinding::inflate) {

    override fun initView() {
        Utility.setImage(
            requireContext(),
            binding.image,
            "https://d5nunyagcicgy.cloudfront.net/external_assets/hero_examples/hair_beach_v391182663/original.jpeg"
        )
        binding.image.setOnClickListener {
            NavigationManager.getInstance().openFragmentBottomUp(M06DemoFragment())
        }
    }


    override fun getData() {

    }

}