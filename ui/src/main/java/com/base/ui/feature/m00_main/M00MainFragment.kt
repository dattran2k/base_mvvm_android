package com.base.ui.feature.m00_main

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.base.core.designsystem.R
import com.base.core.designsystem.component.CustomTab
import com.base.ui.databinding.M00FragmentMainBinding
import com.base.ui.feature.BaseFragment
import com.base.ui.feature.m01_home.M01HomeFragment
import com.base.ui.feature.m02_trend.M02TrendFragment
import com.base.ui.feature.m03_discover.M03DiscoverFragment
import com.base.ui.feature.m04_notification.M04NotificationFragment
import com.base.ui.feature.m05_user.M05UserFragment
import com.google.android.material.tabs.TabLayoutMediator


class M00MainFragment : BaseFragment<M00FragmentMainBinding>(M00FragmentMainBinding::inflate) {

    data class HomeTabData(@DrawableRes val icon: Int, @StringRes val title: Int)
    private val listTab = listOf(
        HomeTabData(R.drawable.ic_tab_home, R.string.tab_home),
        HomeTabData(R.drawable.ic_tab_trend, R.string.tab_trend),
        HomeTabData(R.drawable.ic_tab_discover, R.string.tab_discover),
        HomeTabData(R.drawable.ic_tab_notification, R.string.tab_notification),
        HomeTabData(R.drawable.ic_tab_user, R.string.tab_user),
    )

    private var pagerAdapter: MainViewPagerAdapter<Any, Fragment>? = null

    override fun initView() {
        pagerAdapter = MainViewPagerAdapter(this, listTab) { _, position ->
            when (position) {
                0 -> M01HomeFragment()
                1 -> M02TrendFragment()
                2 -> M03DiscoverFragment()
                3 -> M04NotificationFragment()
                else -> M05UserFragment()
            }
        }
        binding.pager.adapter = pagerAdapter
        binding.pager.offscreenPageLimit = listTab.size
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.customView = getCustomViewTab(
                ContextCompat.getDrawable(
                    requireContext(),
                    listTab[position].icon
                ),
                getString(listTab[position].title),
            )
        }.attach()

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