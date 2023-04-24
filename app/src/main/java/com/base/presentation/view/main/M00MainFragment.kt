package com.base.presentation.view.main

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.base.R
import com.base.base.BaseFragment
import com.base.common.Constants.TabHome.TAB_DISCOVER_POSITION
import com.base.common.Constants.TabHome.TAB_HOME_POSITION
import com.base.common.Constants.TabHome.TAB_NOTIFICATION_POSITION
import com.base.common.Constants.TabHome.TAB_TREND_POSITION
import com.base.common.Constants.TabHome.TAB_USER_USER_POSITION
import com.base.databinding.M00FragmentMainBinding
import com.base.presentation.view.main.m01_home.M01HomeFragment
import com.base.presentation.view.main.m02_trend.M02TrendFragment
import com.base.presentation.view.main.m03_discover.M03DiscoverFragment
import com.base.presentation.view.main.m04_notification.M04NotificationFragment
import com.base.presentation.view.main.m05_user.M05UserFragment
import com.base.presentation.widget.CustomTab
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class M00MainFragment : BaseFragment<M00FragmentMainBinding>(M00FragmentMainBinding::inflate) {

    private var pagerAdapter: HomeViewPagerAdapter<Fragment>? = null

    private val listIcon = listOf(
        R.drawable.ic_tab_home,
        R.drawable.ic_tab_trend,
        R.drawable.ic_tab_discover,
        R.drawable.ic_tab_notification,
        R.drawable.ic_tab_user
    )
    private val listText = listOf(
        R.string.tab_home,
        R.string.tab_trend,
        R.string.tab_discover,
        R.string.tab_notification,
        R.string.tab_user,
    )

    override fun initView() {
        pagerAdapter = HomeViewPagerAdapter(this)
        val listFragment = listOf<Fragment>(
            M01HomeFragment(),
            M02TrendFragment(),
            M03DiscoverFragment(),
            M04NotificationFragment(),
            M05UserFragment()
        )
        pagerAdapter?.setListFragment(listFragment)
        binding.pager.adapter = pagerAdapter
        binding.pager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.customView = getCustomViewTab(
                ContextCompat.getDrawable(requireContext(), listIcon[position]),
                getString(listText[position]),
            )
        }.attach()
        binding.pager.offscreenPageLimit = listFragment.size
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                (tab?.customView as CustomTab).isSelected = true
                when (tab.position) {
                    TAB_HOME_POSITION -> {}
                    TAB_TREND_POSITION -> {}
                    TAB_DISCOVER_POSITION -> {}
                    TAB_NOTIFICATION_POSITION -> {}
                    TAB_USER_USER_POSITION -> {}
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                (tab?.customView as CustomTab).isSelected = false
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    TAB_HOME_POSITION -> {}
                    TAB_TREND_POSITION -> {}
                    TAB_DISCOVER_POSITION -> {}
                    TAB_NOTIFICATION_POSITION -> {}
                    TAB_USER_USER_POSITION -> {}
                }
            }

        })
    }

    private fun getCustomViewTab(
        image: Drawable?,
        text: String,
    ): CustomTab {
        val tab = CustomTab(requireContext())
        tab.icon.setImageDrawable(image)
        tab.text.text = text
        return tab
    }

    override fun getData() {
    }

    fun backToHome() {
        try {
            if (binding.pager.currentItem != TAB_HOME_POSITION) {
                binding.pager.setCurrentItem(TAB_HOME_POSITION, false)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}