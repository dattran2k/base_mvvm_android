package com.base.presentation.view.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.base.presentation.base.BaseFragment

class MainViewPagerAdapter<D : Any, T : Fragment>(
    val fragment: Fragment,
    val listData: List<D>,
    private val createFragment: (data: D, position: Int) -> T,
) : FragmentStateAdapter(fragment) {
    private val listFragment: ArrayList<T?> = ArrayList()
    init {
        listFragment.clear()
        listFragment.addAll(listData.map {
            null
        })
    }

    override fun getItemCount() = listData.size

    override fun createFragment(position: Int): T {
        return getFragment(position)
    }


    @Suppress("UNCHECKED_CAST")
    fun getFragment(index: Int): T {
        val findFragment = fragment.childFragmentManager.findFragmentByTag("f$index") as T?
        var fragment = findFragment ?: listFragment[index]
        if (fragment == null) {
            fragment = createFragment.invoke(listData[index], index)
        }
        listFragment[index] = fragment
        return fragment
    }
}