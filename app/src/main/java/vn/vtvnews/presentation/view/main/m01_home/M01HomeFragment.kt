package vn.vtvnews.presentation.view.main.m01_home

import androidx.fragment.app.viewModels
import vn.vtvnews.base.BaseFragment
import vn.vtvnews.databinding.M01FragmentHomeBinding
import vn.vtvnews.helper.NavigationManager
import vn.vtvnews.presentation.view.M06DemoFragment
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