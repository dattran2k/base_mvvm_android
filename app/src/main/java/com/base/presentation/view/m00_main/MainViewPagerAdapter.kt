package com.base.presentation.view.m00_main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainViewPagerAdapter<D : Any, T : Fragment>(
    val fragment: Fragment,
    val listData: List<D>,
    private val createFragment: (data: D, position: Int) -> T,
) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = listData.size

    override fun createFragment(position: Int): T {
        return getFragment(position)
    }


    @Suppress("UNCHECKED_CAST")
    fun getFragment(index: Int): T {
        var fragment = fragment.childFragmentManager.findFragmentByTag("f$index") as T?
        if (fragment == null) {
            fragment = createFragment.invoke(listData[index], index)
        }
        return fragment
    }
}