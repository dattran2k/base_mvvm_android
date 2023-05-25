package com.base.presentation.view.main

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.base.R
import com.base.data.model.HomeTabModel
import com.base.databinding.M00FragmentMainBinding
import com.base.presentation.base.BaseFragment
import com.base.presentation.view.main.m01_home.M01HomeFragment
import com.base.presentation.view.main.m02_trend.M02TrendFragment
import com.base.presentation.view.main.m03_discover.M03DiscoverFragment
import com.base.presentation.view.main.m04_notification.M04NotificationFragment
import com.base.presentation.view.main.m05_user.M05UserFragment
import com.base.presentation.widget.CustomTab
import com.google.android.material.tabs.TabLayoutMediator


class M00MainFragment : BaseFragment<M00FragmentMainBinding>(M00FragmentMainBinding::inflate) {

    private var pagerAdapter: MainViewPagerAdapter? = null
    companion object {
        private val homeTabModel = listOf(
            HomeTabModel(M01HomeFragment::class, R.drawable.ic_tab_home, R.string.tab_home),
            HomeTabModel(M02TrendFragment::class, R.drawable.ic_tab_trend, R.string.tab_trend),
            HomeTabModel(M03DiscoverFragment::class, R.drawable.ic_tab_discover, R.string.tab_discover),
            HomeTabModel(M04NotificationFragment::class, R.drawable.ic_tab_notification, R.string.tab_notification),
            HomeTabModel(M05UserFragment::class, R.drawable.ic_tab_user, R.string.tab_user),
        )
    }
    override fun initView() {
        pagerAdapter = MainViewPagerAdapter(this, homeTabModel)
        binding.pager.adapter = pagerAdapter
        binding.pager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.customView = getCustomViewTab(
                ContextCompat.getDrawable(
                    requireContext(),
                    homeTabModel[position].icon
                ),
                getString(homeTabModel[position].title),
            )
        }.attach()
        binding.pager.offscreenPageLimit = 5
    }

    private fun getCustomViewTab(
        image: Drawable?,
        text: String?,
    ): CustomTab {
        val tab = CustomTab(requireContext())
        tab.binding?.imIcon?.setImageDrawable(image)
        tab.binding?.tvTitle?.text = text
        return tab
    }


}