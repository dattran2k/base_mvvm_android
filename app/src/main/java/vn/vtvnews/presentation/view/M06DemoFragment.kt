package vn.vtvnews.presentation.view

import vn.vtvnews.base.BaseFragment
import vn.vtvnews.databinding.M06FragmentDemoBinding
import vn.vtvnews.helper.NavigationManager
import vn.vtvnews.helper.Utility
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

    override fun initObserver() {

    }

    override fun getData() {

    }

}