package com.base.presentation.view.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.base.data.model.HomeTabModel

class MainViewPagerAdapter(
    fragment: Fragment,
    private val homeTabModel: List<HomeTabModel<out Fragment>>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = homeTabModel.size

    override fun createFragment(position: Int): Fragment{
        return homeTabModel[position].fragment.java.newInstance()
    }
}